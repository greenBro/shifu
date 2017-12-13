package yuangong.id.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import yuangong.id.R;
import yuangong.id.ui.activity.base.BaseActivity;
import yuangong.id.utils.AppUtils;

public class PreferentialDetailActivity extends BaseActivity {


    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_text)
    TextView mTitleText;
    @Bind(R.id.condition1)
    TextView mCondition1;
    @Bind(R.id.finsh_time)
    TextView mFinshTime;
    @Bind(R.id.content_title)
    TextView mTitle;

    @Override
    protected void initData() {
        mTitleBack.setVisibility(View.VISIBLE);
        mTitleText.setText("优惠活动");
        Intent intent = getIntent();
        if (intent == null){
            AppUtils.setToastMsg(context,"加载数据失败");
            return;
        }

        String activity_name = intent.getStringExtra("activity_name");
        //long activity_starttime = intent.getLongExtra("activity_starttime", 0);
        long activity_endtime = intent.getLongExtra("activity_endtime", 0);
        String activity_description = intent.getStringExtra("activity_description");
        mTitle.setText(activity_name);
        mCondition1.setText("  "+activity_description);
        mFinshTime.setText(AppUtils.formatDateF(activity_endtime));
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_preferential_detail;
    }

    @OnClick({R.id.title_back, R.id.title_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
        }
    }
}
