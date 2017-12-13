package yuangong.id.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONObject;

import java.util.HashMap;

import yuangong.id.AppConstant;
import yuangong.id.R;
import yuangong.id.net.NetWorkUtils;
import yuangong.id.ui.activity.TeamManageActivity;
import yuangong.id.view.ClearEditText;

/**
 * Created by Mathy on 2016/9/13 0013.
 * 描述：
 */
public class PopupWindowUtils {

    private TeamManageActivity context;
    private PopupWindow pw;
    private View mVMasker;
    private PullToRefreshListView mListView;

    public void setPopWindow(TeamManageActivity context, View view, View VMasker, PullToRefreshListView listview) {
        this.context = context;
        this.mVMasker = VMasker;
        mListView = listview;
        View inflate = LayoutInflater.from(context).inflate(R.layout.popwindow_add_staff, null);
        ScreenControl control = new ScreenControl(context);

        pw = new PopupWindow(inflate, control.getScreenWide() * 3 / 4, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        final ClearEditText staffName = (ClearEditText) inflate.findViewById(R.id.staff_name);
        final ClearEditText staffPhone = (ClearEditText) inflate.findViewById(R.id.staff_phone);
        TextView quit = (TextView) inflate.findViewById(R.id.quit);
        TextView sure = (TextView) inflate.findViewById(R.id.sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewStaffRequest(staffName.getText().toString(), staffPhone.getText().toString());


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

        pw.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    private void addNewStaffRequest(String staffName, String staffPhone) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("community_info_id", SharePreferenceUtils.getCommunityInfoId(context,AppConstant.USER_SP_NAME));
        if (TextUtils.isEmpty(staffName)){
            AppUtils.setToastMsg(context,"请输入正确的姓名");
            return;
        }
        if (!CheckForAllUtils.isMobileNO(staffPhone)){
            AppUtils.setToastMsg(context,"请输入正确电话号码");
            return;
        }
        params.put("staff_name", staffName);
        params.put("staff_tel", staffPhone);
        NetWorkUtils.requestPHP(context, params, AppConstant.URL_ADD_STAFF, new NetWorkUtils.NetWorkUtilsCallbackPHP() {
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
                mVMasker.setVisibility(View.GONE);
                if (JudgeNetWork.isConnected(context)) {
                    context.onPullDownToRefresh(mListView);
                }else {
                    AppUtils.setToastMsg(context, "当前没有可用网络，刷新失败");
                }

                pw.dismiss();
            }
        });
    }

}
