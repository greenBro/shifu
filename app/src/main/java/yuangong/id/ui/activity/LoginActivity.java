package yuangong.id.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yuangong.id.utils.AppUtils;
import yuangong.id.utils.IntentUtils;
import yuangong.id.utils.SharePreferenceUtils;
import yuangong.id.AppConstant;
import yuangong.id.R;
import yuangong.id.bean.LoginBean;
import yuangong.id.net.NetWorkUtils;

/**
 * Created by Administrator on 2016/8/23.
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.user)
    EditText user;
    @Bind(R.id.passworld)
    EditText passworld;
    @Bind(R.id.loging_go)
    TextView logingGo;
    @Bind(R.id.login_back)
    LinearLayout mParentView;
    private boolean mLoginStatus;
    private long exitTime;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginStatus = SharePreferenceUtils.getLoginStatus(this, AppConstant.USER_SP_NAME);
        if (mLoginStatus) {
            IntentUtils.startActivity(this, MainActivity.class);
            finish();
            return;
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        mParentView.setBackgroundResource(R.mipmap.xiaomiback);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //进入到这里代表没有权限.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 12);
            }

            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission_group.STORAGE}, 12);
        }
    }

    @OnClick({R.id.loging_go})
    public void onClick(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)  {
            //进入到这里代表没有权限.

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                /*//已经禁止提示了
                Toast.makeText(LoginActivity.this, "您已禁止该权限，需要重新开启。", Toast.LENGTH_SHORT).show();*/
                setDialog();
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission_group.STORAGE}, 12);
            }

        }else {

            String s = user.getText().toString();
            String s1 = passworld.getText().toString();
            if (TextUtils.isEmpty(s) || TextUtils.isEmpty(s.trim())) {
                user.setText("");
                Toast.makeText(LoginActivity.this, "请填写账号", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(s1) || TextUtils.isEmpty(s1.trim())) {
                user.setText("");
                Toast.makeText(LoginActivity.this, "请填写密码", Toast.LENGTH_SHORT).show();
            } else {
                submit(s, s1);
            }
        }
    }

    private void submit(final String phone, final String password) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("staff_tel", phone);

        final String md5Password = AppUtils.getMD5Password(password);
        //System.out.println("=====>md5Password= " + md5Password);
        NetWorkUtils.request(LoginActivity.this, params, AppConstant.URL_LOGIN, LoginBean.class, new NetWorkUtils.NetWorkUtilsCallback<LoginBean>() {
            @Override
            public void onError(int id) {
                NetWorkUtils.cacheMiss(LoginActivity.this, id);
            }

            @Override
            public void onWarn(String msg) {
                AppUtils.setToastMsg(LoginActivity.this, msg);
            }

            @Override
            public void onResponse(LoginBean loginBean) {

               //匹配密码
                String staff_password = loginBean.getResult().getStaff_password();
                //System.out.println("=======>staff_password = " + staff_password);
                if (!staff_password.equals(md5Password)) {
                    AppUtils.setToastMsg(LoginActivity.this, "密码有误，请重新输入");
                    return;
                }

                //成功登陆
                AppUtils.setToastMsg(LoginActivity.this, "登录成功");

                //存储用户信息
                String s = new Gson().toJson(loginBean.getResult());
                SharePreferenceUtils.saveLoginMsg(LoginActivity.this, AppConstant.USER_SP_NAME, phone, password, s);

                //存储登录状态
                SharePreferenceUtils.saveLoginStatus(LoginActivity.this, AppConstant.USER_SP_NAME, true);
                IntentUtils.startActivity(LoginActivity.this, MainActivity.class);
                finish();
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {

        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 12:
                if(grantResults.length >0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //用户同意授权
                }else{
                    setDialog();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private void setDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("警告")
                .setMessage("没有文件读取权限，请设置。立刻设置")
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent =new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent,2);
                        mAlertDialog.dismiss();
                    }
                });
        mAlertDialog = builder.create();
        mAlertDialog.dismiss();
    }


}
