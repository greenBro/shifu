package yuangong.id.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import yuangong.id.AppConstant;
import yuangong.id.adapter.NotAccomplishAdapter;
import yuangong.id.bean.NotAccomplishBean;
import yuangong.id.net.NetWorkUtils;
import yuangong.id.ui.activity.base.ListViewActivity;
import yuangong.id.utils.AppUtils;
import yuangong.id.R;
import yuangong.id.utils.JSONUtils;
import yuangong.id.utils.JudgeNetWork;
import yuangong.id.utils.SharePreferenceUtils;
import yuangong.id.view.ClearEditText;

public class NotAccomplishActivity extends ListViewActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title)
    RelativeLayout mTitle;
    @Bind(R.id.title_text)
    TextView mTitleText;
    @Bind(R.id.title_right_text1)
    TextView mTitleRightText;
    @Bind(R.id.search_content)
    ClearEditText mSearchContent;
    @Bind(R.id.spinner)
    Spinner mSpinner;
    @Bind(R.id.listview)
    PullToRefreshListView mListview;
    @Bind(R.id.vMasker)
    View mVMasker;
    private List<NotAccomplishBean> mResultBeans;
    private NotAccomplishAdapter mAdapter;
    private int dayNumber = 1; //搜索天数限制
    private String reserviceType = ""; //预约项目类型 0.所有 1.洗车 2.美容
    private String name = "";
    private String moblie = "";
    private String car_info_plate = "";
    private int page = 1;
    private int searchType = 0; // 搜索类型   0 手机号； 1 车牌号； 2用户名
    private static final String NOT_ACCOMPLISH = "NOTACCOMPLISH";


    @Override
    protected void bindAdapter() {
        mResultBeans = new ArrayList<>();
        List<NotAccomplishBean> notAccomplish = SharePreferenceUtils.getNotAccomplish(context, AppConstant.USER_SP_NAME, NOT_ACCOMPLISH);
        if (notAccomplish != null) {
            mResultBeans.addAll(notAccomplish);
        }
        mAdapter = new NotAccomplishAdapter(context, mResultBeans);
        mListview.setAdapter(mAdapter);
        mListview.setMode(PullToRefreshBase.Mode.BOTH);
        setPullRefreshListView(mListview, mAdapter);
        if (JudgeNetWork.isConnected(context)) {
            setADD();
        }
    }

    @Override
    protected void initData() {
        mTitleText.setText("未清洗任务列表");
        mTitleRightText.setVisibility(View.VISIBLE);
        mTitleBack.setVisibility(View.VISIBLE);
        mTitleRightText.setText("筛选");

        //获取洗车工信息
        getWashStaffId();
    }

    private void getWashStaffId() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("community_info_id", SharePreferenceUtils.getCommunityInfoId(context, AppConstant.USER_SP_NAME));
        NetWorkUtils.requestPHP(context, params, AppConstant.URL_STAFF_LIST, new NetWorkUtils.NetWorkUtilsCallbackPHP() {
            @Override
            public void onError(int id) {
                NetWorkUtils.cacheMiss(context, id);
            }

            @Override
            public void onWarn(String msg) {
                //AppUtils.setToastMsg(context, msg);
            }

            @Override
            public void onResponse(JSONObject t) {
                JSONArray jsonArray = t.optJSONArray("result");
                if (jsonArray != null && jsonArray.length() > 0) {
                    SharePreferenceUtils.saveWashStaffInfo(context, AppConstant.USER_SP_NAME, "washStaff", t.toString());
                }
            }
        });
    }

    /**
     * 网络请求
     */
    private void request(int dayNum, final int page, final String reserveType, String name, String mobile, String car_info_plate) {


        Calendar instance = Calendar.getInstance();
        int i = instance.get(Calendar.HOUR_OF_DAY);

        long beginTime = 0;//当前时间
        Calendar instance1 = Calendar.getInstance();
        if (i < 6) {

            instance1.set(instance.get(Calendar.YEAR),
                    instance.get(Calendar.MONTH),
                    instance.get(Calendar.DATE) - 1, 0, 0, 0);
            beginTime = instance1.getTimeInMillis() / 1000;
        } else {
            instance1.set(instance.get(Calendar.YEAR),
                    instance.get(Calendar.MONTH),
                    instance.get(Calendar.DATE), 0, 0, 0);
            beginTime = instance1.getTimeInMillis() / 1000;
        }
        long endTime = beginTime + 24 * 60 * 60 * dayNum;//开始时间
        System.out.println("=====>beginTime=" + beginTime + " ,endTime=" + endTime);
        //System.out.println("=====>reserve_time=" + AppUtils.formatDateF(AppUtils.getStartTime()));
        //System.out.println("=====>beginTime=" + AppUtils.formatDate(beginTime * 1000) + " ,endTime=" + AppUtils.formatDate(endTime * 1000));

        HashMap<String, Object> params = new HashMap<>();
        String communityInfoId = SharePreferenceUtils.getCommunityInfoId(context, AppConstant.USER_SP_NAME);
        //System.out.println("responseBody"+communityInfoId+"begin_time"+beginTime+"end_time"+endTime+"i"+i);
        params.put("community_info_id", communityInfoId);
        params.put("begin_time", beginTime);
        params.put("end_time", endTime);
        params.put("page", page);
        params.put("limit", 10);
        params.put("reserveType", reserveType);
        params.put("reserve_type", reserveType);
        //System.out.println("=======>reserveType = " + reserveType);
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

        NetWorkUtils.requestPHP(context, params, AppConstant.URL_NOT_ACCOMPLISH, new NetWorkUtils.NetWorkUtilsCallbackPHP() {

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
                //System.out.println("====>t=" +t.toString());
                if (page == 1) {
                    mResultBeans.clear();
                }
                if (jsonArray == null || jsonArray.length() < 1) {
                    AppUtils.setToastMsg(context, "没有数据");
                    mAdapter.notifyDataSetChanged();
                    //reserviceType = "0";
                    onrequestDone();
                    return;
                }

                List<NotAccomplishBean> beanList = JSONUtils.getBeanList(jsonArray, NotAccomplishBean.class);
                mResultBeans.addAll(beanList);
                mAdapter.notifyDataSetChanged();
                onrequestDone();
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
        return R.layout.activity_not_accomplish;
    }


    @OnClick({R.id.title_back, R.id.title_right_text1, R.id.search_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_right_text1:  //筛选
                setFilterPopWindow(context, mTitle, mVMasker);
                mVMasker.setVisibility(View.VISIBLE);
                break;
            case R.id.search_btn:  //搜索
                if (!JudgeNetWork.isConnected(context)) {
                    AppUtils.setToastMsg(context, "没有网络");
                    return;
                }
                String s = mSearchContent.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    AppUtils.setToastMsg(context, "请输入要搜索的信息");
                    return;
                }
                switch (searchType) {
                    case 0://手机号
                        moblie = s;
                        break;
                    case 1://车牌
                        car_info_plate = s;
                        break;
                    case 2: //用户名
                        name = s;
                        break;
                }
                //System.out.println("======>111111");
                request(dayNumber, page, reserviceType, name, moblie, car_info_plate);
                break;
        }
    }

    /**
     * 设置筛选PopWindow
     *
     * @param context
     * @param view
     * @param mVMasker
     */
    public void setFilterPopWindow(final Context context, View view, final View mVMasker) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.popwindow_filter, null);

        final PopupWindow pw = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        RadioGroup time = (RadioGroup) inflate.findViewById(R.id.time);
        RadioGroup service_type = (RadioGroup) inflate.findViewById(R.id.service_type);
        RadioButton one = (RadioButton) inflate.findViewById(R.id.one);
        RadioButton service_all = (RadioButton) inflate.findViewById(R.id.service_all);
        one.setChecked(true);
        service_all.setChecked(true);
        //天数
        time.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int childCount = group.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    RadioButton rb = (RadioButton) group.getChildAt(i);
                    if (rb.isChecked()) {
                        switch (i) {
                            case 0:
                                dayNumber = 1;
                                break;
                            case 1:
                                dayNumber = 3;
                                break;
                            case 2:
                                dayNumber = 5;
                                break;
                            case 3:
                                dayNumber = 7;
                                break;
                        }
                    }
                }
            }
        });
        //服务类型
        service_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int childCount = group.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    RadioButton rb = (RadioButton) group.getChildAt(i);
                    if (rb.isChecked()) {
                        switch (i) {
                            case 0:
                                reserviceType = "0";//所有
                                break;
                            case 1:
                                reserviceType = "1"; //洗车
                                break;
                            case 2:
                                reserviceType = "2";//美容
                                break;
                        }
                    }
                }
            }
        });
        TextView quit = (TextView) inflate.findViewById(R.id.quit);
        TextView sure = (TextView) inflate.findViewById(R.id.sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = 1;

               // System.out.println("=======>reserveType = " + reserviceType);
                //System.out.println("======>222222");
                request(dayNumber, page, reserviceType, null, null, null);
                dayNumber = 1;
                reserviceType = "0";

                pw.dismiss();
                mVMasker.setVisibility(View.GONE);
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
                mVMasker.setVisibility(View.GONE);
            }
        });

        pw.setOutsideTouchable(false);
        pw.showAsDropDown(view);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NotAccomplishBean notAccomplishBean = mResultBeans.get(position - 1);
        Intent intent = new Intent(context, NotAccomplishDetailActivity.class);

        intent.putExtra("package_count_type", notAccomplishBean.getPackage_count_type());
        intent.putExtra("position", position - 1);
        intent.putExtra("username", notAccomplishBean.getName());
        intent.putExtra("phone", notAccomplishBean.getMobile());
        intent.putExtra("layer_name", notAccomplishBean.getLayer_name());
        intent.putExtra("car_info_park", notAccomplishBean.getCar_info_park());
        intent.putExtra("reserve_status", notAccomplishBean.getReserve_status());
        intent.putExtra("package_name", notAccomplishBean.getPackage_name());
        intent.putExtra("reserve_time", notAccomplishBean.getReserve_time());
        intent.putExtra("car_info_plate", notAccomplishBean.getCar_info_plate());
        intent.putExtra("order_tip", notAccomplishBean.getReserve_info_tip()); //备注
        intent.putExtra("order_id", notAccomplishBean.getOrder_id());
        intent.putExtra("reserve_info_id", notAccomplishBean.getReserve_info_id());
        intent.putExtra("reserve_type", notAccomplishBean.getReserve_type());
        startActivityForResult(intent, 200);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK) {
            if (JudgeNetWork.isConnected(context)) {
                onPullDownToRefresh(mListview);
            } else {

                int position = data.getIntExtra("position", 0);
                int clearStatus = data.getIntExtra("clearStatus", 0);

                if (mResultBeans != null && mResultBeans.size() > 0) {
                    /*String clear_before1 = data.getStringExtra("clear_before1");
                    String clear_before2 = data.getStringExtra("clear_before2");
                    String clear_after1 = data.getStringExtra("clear_after1");
                    String clear_after2 = data.getStringExtra("clear_after2");
                    String clear_before_ids = data.getStringExtra("clear_before_ids");
                    String clear_after_ids = data.getStringExtra("clear_after_ids");*/
                    NotAccomplishBean notAccomplishBean = mResultBeans.get(position);
                    notAccomplishBean.setIsClear(true);
                  /*notAccomplishBean.setClear_before_ids(clear_before_ids);
                    notAccomplishBean.setClear_after_ids(clear_after_ids);
                    notAccomplishBean.setClear_before1(clear_before1);
                    notAccomplishBean.setClear_before2(clear_before2);
                    notAccomplishBean.setClear_after1(clear_after1);
                    notAccomplishBean.setClear_after1(clear_after2);*/
                    SharePreferenceUtils.saveNotAccomplishActivity(context, AppConstant.USER_SP_NAME, NOT_ACCOMPLISH, new Gson().toJson(mResultBeans));
                    mAdapter.notifyDataSetChanged();
                }
                AppUtils.setToastMsg(context, "当前没有可用网络，刷新失败");
            }
        }
    }

    //////////////////////////下拉刷新监听///////////////////////

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        if (JudgeNetWork.isConnected(context)) {
            page = 1;
            name = "";
            moblie = "";
            car_info_plate = "";
            reserviceType="0";
            reserviceType = "0";
            //进入界面加载数据
            request(dayNumber, page, reserviceType, null, null, null);
        } else {
            onrequestDone();
            AppUtils.setToastMsg(context, "没有网络");
        }
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        request(dayNumber, page, reserviceType, name, moblie, car_info_plate);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mResultBeans.size() > 0) {
            SharePreferenceUtils.saveNotAccomplishActivity(context, AppConstant.USER_SP_NAME, NOT_ACCOMPLISH, new Gson().toJson(mResultBeans));
            //Log.i("TGA", 111111 + "");
        }

    }
}
