package yuangong.id.ui.activity;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.Bind;
import yuangong.id.AppConstant;
import yuangong.id.R;
import yuangong.id.bean.AboutUsBean;
import yuangong.id.net.NetWorkUtils;
import yuangong.id.ui.activity.base.BaseActivity;
import yuangong.id.utils.AppUtils;

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_text)
    TextView mTitleText;
    @Bind(R.id.about_msg)
    TextView mAboutMsg;

    @Override
    protected void initData() {
        mTitleBack.setVisibility(View.VISIBLE);
        mTitleText.setText("关于微特尔");
        NetWorkUtils.request(context, new HashMap<String, Object>(), AppConstant.ABOUT_US, AboutUsBean.class, new NetWorkUtils.NetWorkUtilsCallback<AboutUsBean>() {
            @Override
            public void onError(int id) {
                NetWorkUtils.cacheMiss(context,id);
            }

            @Override
            public void onWarn(String msg) {
                AppUtils.setToastMsg(context, msg);
            }

            @Override
            public void onResponse(AboutUsBean aboutUsBean) {
                mAboutMsg.setText( "  "+aboutUsBean.getResult().getContent());
            }
        });
    }

    @Override
    protected void setListener() {
        mTitleBack.setOnClickListener(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_about_us;
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
