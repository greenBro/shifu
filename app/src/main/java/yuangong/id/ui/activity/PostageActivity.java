package yuangong.id.ui.activity;


import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import yuangong.id.R;
import yuangong.id.adapter.PostageAdapter;
import yuangong.id.bean.CarTypeBean;
import yuangong.id.bean.PostageBean;
import yuangong.id.net.NetWorkUtils;
import yuangong.id.ui.activity.base.ListViewActivity;
import yuangong.id.utils.AppUtils;
import yuangong.id.utils.JSONUtils;
import yuangong.id.utils.SharePreferenceUtils;

public class PostageActivity extends ListViewActivity {


    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_text)
    TextView mTitleText;
    @Bind(R.id.listview)
    PullToRefreshListView mListview;
   /* @Bind(R.id.vMasker)
    View mVMasker;*/
    private PostageAdapter mAdapter;
    private List<PostageBean> mPostageBeans;

    private int page = 1; //页数
    private String package_type = ""; //产品类型
    private String car_type_id = "";//车类型
    private RadioButton mRb1;
    private List<String> mStrings;
    private List<String> mArrayList;


    @Override
    protected void bindAdapter() {
        mPostageBeans = new ArrayList<>();
        mAdapter = new PostageAdapter(context, mPostageBeans);
        mListview.setAdapter(mAdapter);
        mListview.setMode(PullToRefreshBase.Mode.BOTH);
        setPullRefreshListView(mListview, mAdapter);
        setADD();
    }

    @Override
    protected void initData() {
        mTitleBack.setVisibility(View.VISIBLE);
        mTitleText.setText("资费详情");
        //获取汽车类型
        getCarTypeRequest();
    }

    @Override
    protected void setListener() {

    }

    //获取汽车类型
    private void getCarTypeRequest() {
        HashMap<String, Object> params = new HashMap<>();
        NetWorkUtils.requestPHP(context, params, AppConstant.URL_GET_CAR_TYPE, new NetWorkUtils.NetWorkUtilsCallbackPHP() {
            @Override
            public void onError(int id) {
                NetWorkUtils.cacheMiss(context, id);
            }

            @Override
            public void onWarn(String msg) {
                AppUtils.setToastMsg(context, msg);
            }

            @Override
            public void onResponse(JSONObject t) {
                JSONArray jsonArray = t.optJSONArray("result");
                if (jsonArray == null) {
                    AppUtils.setToastMsg(context, "没有数据");
                    onrequestDone();
                    return;
                }
                List<CarTypeBean> mCarTypeBeanList = JSONUtils.getBeanList(jsonArray, CarTypeBean.class);
                mArrayList = new ArrayList<String>();
                int size = mCarTypeBeanList.size();
                for (int i = 0; i < size; i++) {
                    mArrayList.add(mCarTypeBeanList.get(i).getCar_type());
                }
            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_postage;
    }


    @OnClick({R.id.title_back, R.id.product_type, R.id.car_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.product_type: //产品类型
               // mVMasker.setVisibility(View.VISIBLE);
                View contentView = getLayoutInflater().inflate(R.layout.popupwindow_product_type, null);
                final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT/*screenControl.getScreenWide() / 2*/,
                        ViewGroup.LayoutParams.MATCH_PARENT, true);

                RadioGroup type = (RadioGroup) contentView.findViewById(R.id.rg);

                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.setOutsideTouchable(true);

                popupWindow.showAsDropDown(view);
                type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        int childCount = group.getChildCount();
                        for (int i = 0; i < childCount; i++) {
                            RadioButton rb = (RadioButton) group.getChildAt(i);
                            if (rb.isChecked()) {
                                package_type = i + "";
                                //System.out.println("package_type=" + package_type);
                                request(package_type, car_type_id);
                            }
                        }
                        popupWindow.dismiss();
                        //mVMasker.setVisibility(View.GONE);
                    }
                });

                for (int i = 0; i < 6; i++) {
                    RadioButton rbutton = (RadioButton) type.getChildAt(i);
                    if (package_type.equals(i+"")){

                        rbutton.setBackgroundColor(getResources().getColor(R.color.text_blue));
                    }else {
                        rbutton.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                }
                //mVMasker.setVisibility(View.GONE);
                break;
            case R.id.car_type: //汽车类型
                //mVMasker.setVisibility(View.VISIBLE);
                View carTypeView = getLayoutInflater().inflate(R.layout.popupwindow_car_type, null);
                ListView carTypeList = (ListView) carTypeView.findViewById(R.id.car_type_list);
                final PopupWindow carTypePopupWindow = new PopupWindow(carTypeView, ViewGroup.LayoutParams.MATCH_PARENT/*screenControl.getScreenWide() / 2*/,
                        ViewGroup.LayoutParams.MATCH_PARENT, true);

                final ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.cartype_item, R.id.car_type_tv, mArrayList){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        final View view1 = super.getView(position, convertView, parent);
                        if(car_type_id.equals(position+"")){
                            view1.setBackgroundColor(getResources().getColor(R.color.text_blue));
                        }else {
                            view1.setBackgroundColor(getResources().getColor(R.color.white));
                        }
                        return view1;
                    }
                };
                carTypeList.setAdapter(arrayAdapter);
                carTypeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        car_type_id = position + "";
                        arrayAdapter.notifyDataSetChanged();
                        request(package_type, car_type_id);
                        carTypePopupWindow.dismiss();

                    }
                });
                carTypePopupWindow.setBackgroundDrawable(new ColorDrawable());
                carTypePopupWindow.setOutsideTouchable(true);

                carTypePopupWindow.showAsDropDown(view);

                break;
        }
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page = 1;
        package_type="";
        car_type_id = "";
        request(null, null);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        request(package_type,car_type_id);
    }

    //获取套餐信息
    private void request(String package_type, String car_type_id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("community_info_id", SharePreferenceUtils.getCommunityInfoId(context, AppConstant.USER_SP_NAME));
        params.put("page", page);
        params.put("limit", 10);

        if (!TextUtils.isEmpty(package_type)) {
            params.put("package_type", package_type);
        }
        if (!TextUtils.isEmpty(car_type_id)) {
            params.put("car_type_id", car_type_id);
        }
        NetWorkUtils.requestPHP(context, params, AppConstant.GET_POSTAGE_DATA, new NetWorkUtils.NetWorkUtilsCallbackPHP() {
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
                    mPostageBeans.clear();
                }
                if (jsonArray == null) {
                    AppUtils.setToastMsg(context, "没有数据");
                    onrequestDone();
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                List<PostageBean> beanList = JSONUtils.getBeanList(jsonArray, PostageBean.class);
                mPostageBeans.addAll(beanList);
                mAdapter.notifyDataSetChanged();
                onrequestDone();
            }
        });
    }

}
