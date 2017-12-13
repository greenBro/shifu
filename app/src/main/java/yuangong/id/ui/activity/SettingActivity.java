package yuangong.id.ui.activity;


import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import yuangong.id.AppConstant;
import yuangong.id.ui.activity.base.BaseActivity;
import yuangong.id.utils.ActivityCollector;
import yuangong.id.utils.IntentUtils;
import yuangong.id.R;
import yuangong.id.utils.SharePreferenceUtils;

public class SettingActivity extends BaseActivity {

    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_text)
    TextView mTitleText;
    @Bind(R.id.change_password)
    RelativeLayout mChangePassword;
    @Bind(R.id.about_us)
    RelativeLayout mAboutUs;
    @Bind(R.id.now_version)
    RelativeLayout mNowVersion;
    private AlertDialog mAlertDialog;

    @Override
    protected void initData() {
        mTitleBack.setVisibility(View.VISIBLE);
        mTitleText.setText("设置");
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_setting;
    }

    @OnClick({R.id.quit,R.id.title_back, R.id.change_password, R.id.about_us, R.id.now_version})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.change_password:
                IntentUtils.startActivity(context, ChangePasswordActivity.class);
                break;
            case R.id.about_us:
                IntentUtils.startActivity(context,AboutUsActivity.class);
                break;
            case R.id.now_version:
                break;
            case R.id.quit:
                SharePreferenceUtils.saveLoginStatus(context, AppConstant.USER_SP_NAME,false);
                //关闭所有Activity
                ActivityCollector.finshAll();
                IntentUtils.startActivity(context, LoginActivity.class);
                break;


        }
    }
}
