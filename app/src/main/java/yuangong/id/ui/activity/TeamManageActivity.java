package yuangong.id.ui.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import yuangong.id.bean.AddressBean;
import yuangong.id.bean.StaffListBean;
import yuangong.id.net.NetWorkUtils;
import yuangong.id.ui.activity.base.ListViewActivity;
import yuangong.id.utils.AppUtils;
import yuangong.id.utils.JSONUtils;
import yuangong.id.utils.JudgeNetWork;
import yuangong.id.utils.PopupWindowUtils;
import yuangong.id.R;
import yuangong.id.adapter.TeamManageAdapter;
import yuangong.id.utils.SharePreferenceUtils;

public class TeamManageActivity extends ListViewActivity implements CompoundButton.OnCheckedChangeListener {

    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_text)
    TextView mTitleText;
    //@Bind(R.id.title_right_text)
    //ToggleButton mTitleRight;
    @Bind(R.id.listview)
    PullToRefreshListView mListview;
    @Bind(R.id.vMasker)
    View mVMasker;
    @Bind(R.id.add_new_staff)
    TextView mAddNewStaff;
    private TeamManageAdapter mAdapter;
    private List<StaffListBean> mListBeans = new ArrayList<>();
    private List<AddressBean> beanList;
    private AlertDialog.Builder transferDialog;
    private int page = 1;


    @Override
    protected void bindAdapter() {
        mAdapter = new TeamManageAdapter(context, mListBeans);
        mListview.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

        setPullRefreshListView(mListview, mAdapter);
        setADD();
    }

    @Override
    protected void initData() {
        mTitleBack.setVisibility(View.VISIBLE);
        mTitleText.setText("团队管理");
        //mTitleRight.setVisibility(View.VISIBLE);
        beanList = new ArrayList<>();

    }


    /**
     * 网络请求
     */
    private void request() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("community_info_id", SharePreferenceUtils.getCommunityInfoId(context, AppConstant.USER_SP_NAME));

        NetWorkUtils.requestPHP(context, params, AppConstant.URL_STAFF_LIST, new NetWorkUtils.NetWorkUtilsCallbackPHP() {

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
                mListBeans.clear();
                if (jsonArray == null) {
                    AppUtils.setToastMsg(context, "没有数据");
                    mAdapter.notifyDataSetChanged();
                    onrequestDone();
                    return;
                }
                List<StaffListBean> beanList = JSONUtils.getBeanList(jsonArray, StaffListBean.class);
                mListBeans.addAll(beanList);
                onrequestDone();
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void setListener() {
        //mTitleRight.setOnCheckedChangeListener(this);
        //转移员工
        mAdapter.setOnTeamTransferListener(new TeamManageAdapter.OnTeamTransferListener() {
            @Override
            public void teamTransfer(String uid) {
                getAddress(uid);
            }
        });

        //删除员工
        mAdapter.setOnDeleteStaffListener(new TeamManageAdapter.OnDeleteStaffListener() {
            @Override
            public void deleteStaff(String staffId) {
                deleteStaffRequest(staffId);
            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_team_manage;
    }


    @OnClick({R.id.title_back, R.id.add_new_staff})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.add_new_staff: //添加新员工
                mVMasker.setVisibility(View.VISIBLE);
                new PopupWindowUtils().setPopWindow(TeamManageActivity.this, view, mVMasker,mListview);
                onPullDownToRefresh(mListview);
                if (JudgeNetWork.isConnected(context)) {
                    onPullDownToRefresh(mListview);
                }else {
                    AppUtils.setToastMsg(context, "当前没有可用网络，刷新失败");
                }
                break;
        }
    }

    //删除员工
    private void deleteStaffRequest(String staffId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("staff_id", staffId);
        NetWorkUtils.requestPHP(context, params, AppConstant.URL_DELETE_STAFF, new NetWorkUtils.NetWorkUtilsCallbackPHP() {
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
                AppUtils.setToastMsg(context, t.optString("msg"));
                onPullDownToRefresh(mListview);
            }
        });
    }

    ////////////////////////////////ToggleButton点击事件//////////////////////////////////////////
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.isChecked()) {
            //mRl.setVisibility(View.VISIBLE);
            mAddNewStaff.setVisibility(View.GONE);
            //mAdapter.setCheckBoxVisible(true);

        } else {
            //mAdapter.setCheckBoxVisible(false);
            //mRl.setVisibility(View.GONE);
            mAddNewStaff.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        //System.out.println("======>调用");
        request();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }

    private void showAddressDialog(final List<AddressBean> beanList, final String staffId) {
        transferDialog = new AlertDialog.Builder(this);
        transferDialog.setTitle("请选择服务小区");
        LinearLayout view = new LinearLayout(this);
        view.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        final Spinner spinner1 = new Spinner(this);
        spinner1.setLayoutParams(params);
        final Spinner spinner2 = new Spinner(this);
        spinner1.setLayoutParams(params);
        view.addView(spinner1);
        view.addView(spinner2);
        final int size = beanList.size();
        final List<String> conty = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            AddressBean addressBean = beanList.get(i);
            String community_info_conty = addressBean.getCommunity_info_conty();
            if (!conty.contains(community_info_conty)) {
                conty.add(community_info_conty);
            }
        }
        spinner1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, conty));
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = conty.get(position);
                List<String> name = new ArrayList<>();
                for (int j = 0; j < size; j++) {
                    AddressBean addressBean = beanList.get(j);
                    String community_info_conty = addressBean.getCommunity_info_conty();
                    if (s.equals(community_info_conty)) {
                        name.add(addressBean.getCommunity_info_name());
                    }
                }
                spinner2.setAdapter(new ArrayAdapter<String>(TeamManageActivity.this, android.R.layout.simple_list_item_1, name));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        transferDialog.setView(view);
        transferDialog.setNeutralButton("取消",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                for (int a = 0; a < size; a++) {
                    String selectedItem1 = (String) spinner1.getSelectedItem();
                    String selectedItem2 = (String) spinner2.getSelectedItem();
                    AddressBean addressBean = beanList.get(a);
                    String community_info_conty = addressBean.getCommunity_info_conty();
                    String community_info_name = addressBean.getCommunity_info_name();
                    if (community_info_conty.equals(selectedItem1) && community_info_name.equals(selectedItem2)) {
                        //转移员工网络请求
                        teamTransfer(addressBean.getCommunity_info_id(), staffId);
                        break;
                    } else {
                        continue;
                    }
                }

            }
        });
        transferDialog.show();

    }

    private void getAddress(final String staffId) {
        if (beanList.size() > 1) {
            showAddressDialog(beanList,staffId);
        } else {
            NetWorkUtils.requestPHP(this, new HashMap<String, Object>(), AppConstant.GET_ADDRESS_DATA, new NetWorkUtils.NetWorkUtilsCallbackPHP() {
                @Override
                public void onError(int id) {
                    Toast.makeText(TeamManageActivity.this, "获取地址失败", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onWarn(String msg) {
                    Toast.makeText(TeamManageActivity.this, "获取地址失败", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(JSONObject t) {
                    JSONArray result = t.optJSONArray("result");
                    beanList.addAll(JSONUtils.getBeanList(result, AddressBean.class));
                    showAddressDialog(beanList,staffId);
                }
            });
        }

    }

    /**
     * 转移员工
     *
     * @param addressid
     * @param uid
     */
    private void teamTransfer(String addressid, String uid) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("staff_id",uid);
        params.put("community_info_id",addressid);
        NetWorkUtils.requestPHP(context, params, AppConstant.URL_TRANSFER_STAFF, new NetWorkUtils.NetWorkUtilsCallbackPHP() {
            @Override
            public void onError(int id) {
                NetWorkUtils.cacheMiss(context,id);
            }

            @Override
            public void onWarn(String msg) {
                AppUtils.setToastMsg(context,msg);
            }

            @Override
            public void onResponse(JSONObject t) {
                AppUtils.setToastMsg(context,t.optString("msg"));
                onPullDownToRefresh(mListview);
            }
        });
    }
}
