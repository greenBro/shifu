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

public class BecomeDueDetailActivity extends BaseActivity {


    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_text)
    TextView mTitleText;
    @Bind(R.id.user_name)
    TextView mUserName;
    @Bind(R.id.car_number)
    TextView mCarNumber;
    @Bind(R.id.stop_site)
    TextView mStopSite;
    @Bind(R.id.product_type)
    TextView mProductType;
    @Bind(R.id.product_number)
    TextView mProductNumber;
    @Bind(R.id.order_time)
    TextView mOrderTime;
    @Bind(R.id.user_remark)
    TextView mEndTime;

    @Override
    protected void initData() {
        mTitleBack.setVisibility(View.VISIBLE);
        mTitleText.setText("到期提醒详情");

        Intent intent = getIntent();
        if (intent == null){
            AppUtils.setToastMsg(context, "数据获取失败");
            return;
        }

        String username = intent.getStringExtra("username");
        String phone = intent.getStringExtra("phone");
        String car_info_plate = intent.getStringExtra("car_info_plate");
        String community_layer_info_id = intent.getStringExtra("community_layer_info_id");
        String car_info_park = intent.getStringExtra("car_info_park");
        long start_time = intent.getLongExtra("created_time", 0);
        long end_time = intent.getLongExtra("end_time",0);
        String package_name = intent.getStringExtra("package_name");
        String order_num = intent.getStringExtra("order_num");

        mProductNumber.setText(order_num + "次");
        mUserName.setText(username + " " + phone);
        mCarNumber.setText(car_info_plate);
        mStopSite.setText(community_layer_info_id+car_info_park);
        mProductType.setText(package_name);
        mOrderTime.setText(AppUtils.formatDate(start_time));
        mEndTime.setText(AppUtils.formatDate(end_time));
    }



    @Override
    protected void setListener() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_become_due_detail;
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
