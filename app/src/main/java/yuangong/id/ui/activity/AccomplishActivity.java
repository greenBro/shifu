package yuangong.id.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
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
import yuangong.id.adapter.AccomplishAdapter;
import yuangong.id.bean.AccomplishBean;
import yuangong.id.bean.NotAccomplishBean;
import yuangong.id.net.NetWorkUtils;
import yuangong.id.ui.activity.base.ListViewActivity;
import yuangong.id.utils.AppUtils;
import yuangong.id.R;
import yuangong.id.utils.JSONUtils;
import yuangong.id.utils.SharePreferenceUtils;
import yuangong.id.view.ClearEditText;

public class AccomplishActivity extends ListViewActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_text)
    TextView mTitleText;
    @Bind(R.id.search_content)
    ClearEditText mSearchContent;
    @Bind(R.id.search)
    TextView mSearch;
    @Bind(R.id.spinner)
    Spinner mSpinner;
    @Bind(R.id.listview)
    PullToRefreshListView mListview;
    private List<AccomplishBean> mResultBeans;
    private AccomplishAdapter mAdapter;
    private String url = null;
    private int page = 1;
    private String name = "";
    private String mobile = "";
    private String car_info_plate = "";
    private int searchType = 0; // 搜索类型   0 手机号； 1 车牌号； 2用户名
    private int dayNum = 1;

    @Override
    protected void bindAdapter() {
        mResultBeans = new ArrayList<>();
        mAdapter = new AccomplishAdapter(context, mResultBeans);
        mListview.setAdapter(mAdapter);
        mListview.setMode(PullToRefreshBase.Mode.BOTH);
        setPullRefreshListView(mListview, mAdapter);
        setADD();

    }

    /**
     * 网络请求
     */
    private void request(final int page, String url, String name, String mobile, String car_info_plate) {

        long beginTime = AppUtils.getStartTime() / 1000 - 60 * 60;//开始时间

        long endTime = beginTime + 25 * 60 * 60 * dayNum;//结束时间
        Log.i("TGA", "beginTime=" + beginTime + " endTime= " + endTime);
        HashMap<String, Object> params = new HashMap<>();
        params.put("community_info_id", SharePreferenceUtils.getCommunityInfoId(context, AppConstant.USER_SP_NAME));
        params.put("begin_time", beginTime);
        params.put("end_time", endTime);
        params.put("page", page);
        params.put("limit", 10);

        if (searchType == 0) {
            if (!TextUtils.isEmpty(mobile)) {
                params.put("mobile", mobile);
            }
        } else if (searchType == 1) {
            if (!TextUtils.isEmpty(car_info_plate)) {
                params.put("car_info_plate", car_info_plate);
            }
        } else {
            if (!TextUtils.isEmpty(name)) {
                params.put("name", name);
            }
        }

        NetWorkUtils.requestPHP(context, params, url, new NetWorkUtils.NetWorkUtilsCallbackPHP() {

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
                Log.i("TGA", t.toString());
                if (page == 1) {
                    mResultBeans.clear();
                }
                if (jsonArray == null) {
                    AppUtils.setToastMsg(context, "没有数据");
                    mAdapter.notifyDataSetChanged();
                    onrequestDone();
                    return;
                }
                List<AccomplishBean> beanList = JSONUtils.getBeanList(jsonArray, AccomplishBean.class);
                //System.out.println("========>beanList长度 =" + beanList.size());
                mResultBeans.addAll(beanList);
                mAdapter.notifyDataSetChanged();
                onrequestDone();
            }

        });


    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent == null) {
            AppUtils.setToastMsg(context, "数据异常，请重新打开");
            return;
        }
        int type = intent.getIntExtra("type", 1);
        if (type == 1) {
            mTitleText.setText("已清洗任务列表");
            url = AppConstant.URL_ACCOMPLISH;
        } else {
            mTitleText.setText("总任务列表");
            url = AppConstant.URL_ALL_ACCOMPLISH;
        }
        mTitleBack.setVisibility(View.VISIBLE);
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
                    case 2:
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

    @Override
    protected int getLayoutID() {
        return R.layout.activity_already_accoplish;
    }


    @OnClick({R.id.title_back, R.id.search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.search:
                String s = mSearchContent.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    AppUtils.setToastMsg(context, "请输入要搜索的信息");
                    return;
                }
                switch (searchType) {
                    case 0://手机号
                        mobile = s;
                        break;
                    case 1://车牌
                        car_info_plate = s;
                        break;
                    case 2: //用户名
                        name = s;
                        break;
                }
                request(page, url, name, mobile, car_info_plate);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AccomplishBean accomplishBean = mResultBeans.get(position - 1);
        int reserve_status = accomplishBean.getReserve_status();
        Intent intent = null;
        if (reserve_status == 1) {
            //未清洗
            intent = new Intent(context, NotAccomplishDetailActivity.class);
            intent.putExtra("reserve_time", accomplishBean.getReserve_time());
        } else {
            //清洗完成
            intent = new Intent(context, AccomplishDetailActivity.class);
            AccomplishBean.ClearDetailInfoBean clear_detail_info = accomplishBean.getClear_detail_info();
            String clear_remarks = clear_detail_info.getClear_remarks();
            if (!TextUtils.isEmpty(clear_remarks)) {
                intent.putExtra("clear_remarks", clear_remarks);//员工备注
            } else {
                intent.putExtra("clear_remarks", "");//员工备注
            }

            intent.putExtra("clear_time", clear_detail_info.getCreated_time());//清洗时间
            //清洗前的照片
            List<AccomplishBean.ClearDetailInfoBean.ClearBeforeImgInfoBean> clear_before_img_info1 = clear_detail_info.getClear_before_img_info();
            if (clear_before_img_info1 != null) {
                int size = clear_before_img_info1.size();
                String[] clear_before_img_info = new String[size];
                for (int i = 0; i < size; i++) {
                    clear_before_img_info[i] = clear_before_img_info1.get(i).getPath();
                    Log.i("TGA", "clear_before_img_info" + i + "地址：" + clear_before_img_info[i]);
                }
                intent.putExtra("clear_before_img_info", clear_before_img_info);
            }

            //清洗后的照片
            List<AccomplishBean.ClearDetailInfoBean.ClearAfterImgInfoBean> clear_after_img_info1 = clear_detail_info.getClear_after_img_info();
            if (clear_after_img_info1 != null) {
                int size1 = clear_after_img_info1.size();
                String[] clear_after_img_info = new String[size1];
                for (int i = 0; i < size1; i++) {
                    clear_after_img_info[i] = clear_after_img_info1.get(i).getPath();
                    Log.i("TGA", "clear_after_img_info" + i + "地址：" + clear_after_img_info[i]);
                }
                intent.putExtra("clear_after_img_info", clear_after_img_info);
            }

            //洗车员工
            List<AccomplishBean.StaffInfoBean> staff_info = accomplishBean.getStaff_info();
            if (staff_info != null & staff_info.size() > 0) {
                int size = staff_info.size();
                String staff_name = "";
                if (size > 0 && staff_info != null) {
                    for (int i = 0; i < size; i++) {
                        staff_name = staff_name + " " + staff_info.get(i).getStaff_name();
                    }
                }
                //String staff_name = accomplishBean.getStaff_info().getStaff_name();
                intent.putExtra("servername", staff_name);
            }
        }
        intent.putExtra("username", accomplishBean.getName());
        intent.putExtra("package_count_type", accomplishBean.getPackage_count_type());
        intent.putExtra("phone", accomplishBean.getMobile());
        intent.putExtra("layer_name", accomplishBean.getLayer_name());
        intent.putExtra("car_info_park", accomplishBean.getCar_info_park());
        intent.putExtra("package_name", accomplishBean.getPackage_name());
        intent.putExtra("car_info_plate", accomplishBean.getCar_info_plate());
        intent.putExtra("order_tip", accomplishBean.getOrder_tip()); //用户备注

        startActivity(intent);

}

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page = 1;
        request(page, url, null, null, null);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        request(page, url, name, mobile, car_info_plate);
    }
}
