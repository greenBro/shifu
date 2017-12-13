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
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import yuangong.id.AppConstant;
import yuangong.id.bean.OnLineOrderBean;
import yuangong.id.net.NetWorkUtils;
import yuangong.id.ui.activity.base.ListViewActivity;
import yuangong.id.utils.AppUtils;
import yuangong.id.utils.IntentUtils;
import yuangong.id.R;
import yuangong.id.adapter.OnLineOrderAdapter;
import yuangong.id.utils.JSONUtils;
import yuangong.id.utils.SharePreferenceUtils;
import yuangong.id.view.ClearEditText;

public class OnLineOrderActivity extends ListViewActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_text)
    TextView mTitleText;
    @Bind(R.id.search_content)
    ClearEditText mSearchContent;
    @Bind(R.id.search)
    TextView mSearch;  //搜索按钮
    @Bind(R.id.spinner)
    Spinner mSpinner;
    @Bind(R.id.listview)
    PullToRefreshListView mListview;
    private List<OnLineOrderBean> mOrderBeanList;
    private OnLineOrderAdapter mAdapter;
    private int page = 1;

    private int searchType = 0; // 搜索类型   0 手机号； 1 车牌号
    private String mobile = "";
    private String car_info_plate = "";

    @Override
    protected void bindAdapter() {
        mOrderBeanList = new ArrayList<>();
        mAdapter = new OnLineOrderAdapter(context, mOrderBeanList);
        mListview.setAdapter(mAdapter);
        mListview.setMode(PullToRefreshBase.Mode.BOTH);
        setPullRefreshListView(mListview, mAdapter);
        setADD();
    }


    @Override
    protected void initData() {
        mTitleText.setText("在线订单");
        mTitleBack.setVisibility(View.VISIBLE);
    }

    /**
     * 网络请求
     */
    private void request( String mobile, String car_info_plate) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("community_info_id", SharePreferenceUtils.getCommunityInfoId(context, AppConstant.USER_SP_NAME));
        params.put("page", page);
        params.put("limit", 10);
        if (searchType == 0) {
            if (!TextUtils.isEmpty(mobile)) {
                params.put("mobile", mobile);
            }

        }else {
            if (!TextUtils.isEmpty(car_info_plate)) {
                params.put("car_info_plate", car_info_plate);
            }
        }
        NetWorkUtils.requestPHP(context, params, AppConstant.All_ONLINE_ORDER_LIST, new NetWorkUtils.NetWorkUtilsCallbackPHP() {

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
                    mOrderBeanList.clear();
                }
                if (jsonArray != null) {
                    List<OnLineOrderBean> beanList = JSONUtils.getBeanList(jsonArray, OnLineOrderBean.class);
                    mOrderBeanList.addAll(beanList);
                    mAdapter.notifyDataSetChanged();
                    onrequestDone();
                    return;
                }
                AppUtils.setToastMsg(context, "没有数据");
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
                        searchType = 0;
                        mSearchContent.setHint("请输入搜索的手机号");
                        break;
                    case 1:
                        searchType = 1;
                        mSearchContent.setHint("请输入搜索的车牌号");
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_on_line_order;
    }


    @OnClick({R.id.title_back, R.id.search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.search: //搜索按钮
                String s = mSearchContent.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    AppUtils.setToastMsg(context, "请输入要搜索的信息");
                    return;
                }
                switch (searchType) {
                    case 0:
                        mobile = s;
                        break;
                    case 1:
                        car_info_plate = s;
                        break;
                }
                request(mobile,car_info_plate);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        OnLineOrderBean onLineOrderBean = mOrderBeanList.get(position - 1);
        Intent intent = new Intent(context, TaskDetailActivity.class);
        intent.putExtra("order_name", onLineOrderBean.getName());//订单上的名称
        intent.putExtra("car_info_plate", onLineOrderBean.getCar_info_plate());//车牌号
        intent.putExtra("mobile", onLineOrderBean.getMobile()); //联系地址中的电话
        intent.putExtra("community_layer_info_id", onLineOrderBean.getLayer_name());//楼层
        intent.putExtra("car_info_park", onLineOrderBean.getCar_info_park());//车位号
        intent.putExtra("package_name", onLineOrderBean.getPackage_name());//套餐类型
        intent.putExtra("order_paytime", onLineOrderBean.getCreated_time());//下单时间
        intent.putExtra("true_money", onLineOrderBean.getTrue_money());//订单金额
        intent.putExtra("order_tip", onLineOrderBean.getOrder_tip());//车主备注
        startActivity(intent);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page = 1;
        mobile = "";
        car_info_plate = "";
        request(null,null);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        request(mobile,car_info_plate);
    }
}
