package yuangong.id.ui.activity;


import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.OnClick;
import yuangong.id.AppConstant;
import yuangong.id.bean.LoginBean;
import yuangong.id.net.NetWorkUtils;
import yuangong.id.ui.activity.base.BaseActivity;
import yuangong.id.utils.ActivityCollector;
import yuangong.id.utils.AppUtils;
import yuangong.id.utils.IntentUtils;
import yuangong.id.utils.SharePreferenceUtils;
import yuangong.id.utils.ShowPasswordUtils;
import yuangong.id.R;
import yuangong.id.view.ClearEditText;

public class ChangePasswordActivity extends BaseActivity {

    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_text)
    TextView mTitleText;
    @Bind(R.id.old_password)
    ClearEditText mOldPassword;
    @Bind(R.id.new_password)
    ClearEditText mNewPassword;
    @Bind(R.id.sure_password)
    ClearEditText mSurePassword;
    @Bind(R.id.sure)
    TextView mSure;
    @Bind(R.id.show_password1)
    CheckBox mShowPassword1;
    @Bind(R.id.show_password2)
    CheckBox mShowPassword2;
    @Bind(R.id.show_password3)
    CheckBox mShowPassword3;

    @Override
    protected void initData() {
        mTitleBack.setVisibility(View.VISIBLE);
        mTitleText.setText("修改密码");
    }

    @Override
    protected void setListener() {
        mShowPassword1.setOnCheckedChangeListener(new ShowPasswordUtils(mOldPassword));
        mShowPassword2.setOnCheckedChangeListener(new ShowPasswordUtils(mNewPassword));
        mShowPassword3.setOnCheckedChangeListener(new ShowPasswordUtils(mSurePassword));
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_change_password;
    }


    @OnClick({R.id.title_back, R.id.sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.sure:
                String newPassword = mNewPassword.getText().toString();
                String surePassword = mSurePassword.getText().toString();
                String oldPassword = mOldPassword.getText().toString();
                if (TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(surePassword) ||TextUtils.isEmpty(oldPassword)){
                    AppUtils.setToastMsg(context,"新旧密码不能为空");
                    return;
                }
                if (!newPassword.equals(surePassword)){
                    AppUtils.setToastMsg(context,"新密码两次输入不一致");
                    return;
                }
                String md5Password = AppUtils.getMD5Password(newPassword);
                System.out.println("=====>md5Password= " + md5Password);
                request(md5Password);
                break;
        }
    }

    private void request(String password) {
        HashMap<String, Object> params = new HashMap<>();
        String result = getSharedPreferences(AppConstant.USER_SP_NAME,MODE_PRIVATE).getString("result", "");
        LoginBean.ResultBean resultBean = new Gson().fromJson(result, LoginBean.ResultBean.class);
        params.put("staff_id", resultBean.getStaff_id());
        params.put("newPassword", password);
        NetWorkUtils.requestPHP(context, params, AppConstant.URL_CHANGE_PASSWORD, new NetWorkUtils.NetWorkUtilsCallbackPHP() {
            @Override
            public void onError(int id) {
                NetWorkUtils.cacheMiss(context, id);
            }

            @Override
            public void onWarn(String msg) {

                String substring = msg.substring(10, msg.length());
                AppUtils.setToastMsg(context, substring);
            }


            @Override
            public void onResponse(JSONObject t) {
                String msg = t.optString("msg");
                String substring = msg.substring(10, msg.length());
                AppUtils.setToastMsg(context, substring);
                SharePreferenceUtils.saveLoginStatus(context, AppConstant.USER_SP_NAME, false);
                //关闭所有Activity
                ActivityCollector.finshAll();
                IntentUtils.startActivity(context, LoginActivity.class);
            }
        });
    }
}
