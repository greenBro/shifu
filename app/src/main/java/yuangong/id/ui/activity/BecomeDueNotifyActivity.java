package yuangong.id.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import yuangong.id.AppConstant;
import yuangong.id.bean.BecomeDueBean;
import yuangong.id.net.NetWorkUtils;
import yuangong.id.ui.activity.base.ListViewActivity;
import yuangong.id.utils.AppUtils;
import yuangong.id.R;
import yuangong.id.adapter.BecomeDueAdapter;
import yuangong.id.utils.JSONUtils;
import yuangong.id.utils.SharePreferenceUtils;
import yuangong.id.view.ClearEditText;

public class BecomeDueNotifyActivity extends ListViewActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_text)
    TextView mTitleText;
    @Bind(R.id.search)
    TextView mSearch;
    @Bind(R.id.spinner)
    Spinner mSpinner;
    @Bind(R.id.listview)
    PullToRefreshListView mListview;
    @Bind(R.id.search_content)
    ClearEditText mSearchContent;
    private List<BecomeDueBean> mDueBeanList;
    private BecomeDueAdapter mAdapter;
    private int page = 1;
    private int searchType = 1;//1手机号， 2用户名
    private String name = "";
    private String moblie = "";

    @Override
    protected void bindAdapter() {
        mDueBeanList = new ArrayList<>();
        mAdapter = new BecomeDueAdapter(context, mDueBeanList);
        mListview.setAdapter(mAdapter);
        mListview.setMode(PullToRefreshBase.Mode.BOTH);
        setPullRefreshListView(mListview, mAdapter);
        setADD();
    }

    @Override
    protected void initData() {
        mTitleText.setText("到期提醒");
        mTitleBack.setVisibility(View.VISIBLE);

    }

    /**
     * 网络请求
     */
    private void request(String name,String mobile) {
        long dueTime = 7 * 24 * 60 * 60;//相差时间

        HashMap<String, Object> params = new HashMap<>();
        String communityInfoId = SharePreferenceUtils.getCommunityInfoId(context, AppConstant.USER_SP_NAME);
        //System.out.println("======>currentTimeMillis= " + currentTimeMillis);
        params.put("community_info_id", communityInfoId);
        params.put("second", dueTime);
        params.put("page", page);
        params.put("limit", 10);
        if (searchType == 2){
            if (!TextUtils.isEmpty(name)) {
                params.put("name", name);
            }
        }else {
            if (!TextUtils.isEmpty(moblie)){
                params.put("mobile",mobile);
            }
        }
        NetWorkUtils.requestPHP(context, params, AppConstant.URL_BECOMEDUE_NOTIFY, new NetWorkUtils.NetWorkUtilsCallbackPHP() {

            @Override
            public void onError(int id) {
                NetWorkUtils.cacheMiss(context, id);
                onrequestDone();
            }

            @Override
            public void onWarn(String msg) {
                AppUtils.setToastMsg(context, msg);
                onrequestDone();
            }

            @Override
            public void onResponse(JSONObject t) {
                JSONArray jsonArray = t.optJSONArray("result");
                if (page == 1) {
                    mDueBeanList.clear();
                }
                if (jsonArray == null) {
                    AppUtils.setToastMsg(context, "没有数据");
                    onrequestDone();
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                List<BecomeDueBean> beanList = JSONUtils.getBeanList(jsonArray, BecomeDueBean.class);
                mDueBeanList.addAll(beanList);
                onrequestDone();
                mAdapter.notifyDataSetChanged();
            }

        });


    }

    @Override
    protected void setListener() {
        mListview.setOnItemClickListener(this);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        searchType = 1;
                        mSearchContent.setHint("请输入搜索的电话号码");
                        break;
                    case 1:
                        searchType = 2;
                        mSearchContent.setHint("请输入搜索的用户名");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @OnClick({R.id.title_back, R.id.search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.search:
                String searchContent = mSearchContent.getText().toString();
                if (TextUtils.isEmpty(searchContent)){
                    AppUtils.setToastMsg(context, "请输入要搜索的信息");
                    return;
                }
                switch (searchType) {
                    case 1://手机号
                        moblie = searchContent;
                        break;
                    case 2: //用户名
                        name = searchContent;
                        break;
                }
                request(name,moblie);
                break;
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_become_due_notify;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BecomeDueBean becomeDueBean = mDueBeanList.get(position - 1);
        Intent intent = new Intent(context, BecomeDueDetailActivity.class);

        intent.putExtra("username", becomeDueBean.getName());
        intent.putExtra("phone", becomeDueBean.getMobile());
        intent.putExtra("car_info_plate", becomeDueBean.getCar_info_plate());
        intent.putExtra("community_layer_info_id", becomeDueBean.getLayer_name()); //楼层
        intent.putExtra("car_info_park", becomeDueBean.getCar_info_park());//停放位置
        intent.putExtra("package_name", becomeDueBean.getPackage_count_type());//商品类型
        intent.putExtra("order_num", becomeDueBean.getOrder_num());//剩余次数
        intent.putExtra("created_time", becomeDueBean.getStart_time());//下单时间
        intent.putExtra("end_time", becomeDueBean.getEnd_time());//到期时间
        startActivity(intent);
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page = 1;
        name = "";
        moblie = "";
        request(null,null);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        request(name,moblie);
    }
}
