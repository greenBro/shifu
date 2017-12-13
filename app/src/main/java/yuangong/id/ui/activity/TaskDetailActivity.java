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

public class TaskDetailActivity extends BaseActivity {

    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_text)
    TextView mTitleText;
    @Bind(R.id.user_name)
    TextView mUserName;
    @Bind(R.id.car_number)
    TextView mCarNumber;
    @Bind(R.id.product_type)
    TextView mProductType;
    @Bind(R.id.order_time)
    TextView mOrderTime;
    @Bind(R.id.stop_site)
    TextView mStopSite;
    @Bind(R.id.user_remark)
    TextView mUserRemark;
    @Bind(R.id.order_money)
    TextView mOrderMoney;

    @Override
    protected void initData() {
        mTitleBack.setVisibility(View.VISIBLE);
        mTitleText.setText("任务详情");

        Intent intent = getIntent();
        if (intent == null){
            AppUtils.setToastMsg(context,"数据异常");
            return;
        }
        String order_name = intent.getStringExtra("order_name");//订单中的名称
        String car_info_plate = intent.getStringExtra("car_info_plate");//车牌号
        String mobile = intent.getStringExtra("mobile");//联系电话
        String community_layer_info_id = intent.getStringExtra("community_layer_info_id");//楼层
        String car_info_park = intent.getStringExtra("car_info_park");//车位号
        String package_name = intent.getStringExtra("package_name");//套餐名称
        long order_paytime = intent.getLongExtra("order_paytime", 0);//下单时间
        String true_money = intent.getStringExtra("true_money");//真实价格
        String order_tip = intent.getStringExtra("order_tip");//车主备注
        mUserName.setText(order_name + "  " + mobile);
        mCarNumber.setText(car_info_plate);
        mProductType.setText(package_name);
        mOrderTime.setText(AppUtils.formatDate(order_paytime));
        mStopSite.setText(community_layer_info_id + car_info_park);
        mUserRemark.setText(order_tip);
        mOrderMoney.setText(true_money);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_task_detail;
    }

    @OnClick({R.id.title_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
        }
    }
}
