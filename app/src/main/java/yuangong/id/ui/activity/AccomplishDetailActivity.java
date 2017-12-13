package yuangong.id.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.OnClick;
import yuangong.id.AppConstant;
import yuangong.id.R;
import yuangong.id.ui.activity.base.BaseActivity;
import yuangong.id.utils.AppUtils;
import yuangong.id.utils.FileUtil;
import yuangong.id.utils.ScreenControl;
import yuangong.id.view.DragImageView;

public class AccomplishDetailActivity extends BaseActivity {

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
    @Bind(R.id.clear_status)
    TextView mClearStatus;
    @Bind(R.id.user_remark)
    TextView mUserRemark;
    @Bind(R.id.clear_time)
    TextView mClearTime;
    @Bind(R.id.clear_man)
    TextView mClearMan;
    @Bind(R.id.staff_remark)
    TextView mStaffRemark;
    @Bind(R.id.clear_forward1)
    ImageView mClearForward1;
    @Bind(R.id.clear_forward2)
    ImageView mClearForward2;
    @Bind(R.id.clear_back1)
    ImageView mClearBack1;
    @Bind(R.id.clear_back2)
    ImageView mClearBack2;
    @Bind(R.id.parentView)
    RelativeLayout mParentView;
    @Bind(R.id.big_img)
    DragImageView mBigImg;
  /*  @Bind(R.id.dismiss_img)
    ImageView mDismissImg;*/
    private View mInflate;
    private ImageView mImageView;
    private String mClear_after_img1;
    private String mClear_after_img2;
    private String mClear_before_img1;
    private String mClear_before_img2;


    @Override
    protected void initData() {
        mBigImg.setmActivity(this);
        mTitleBack.setVisibility(View.VISIBLE);
        mTitleText.setText("已清洗任务详情");

        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        String name = intent.getStringExtra("username");
        String phone = intent.getStringExtra("phone");
        String car_info_park = intent.getStringExtra("car_info_park");
        String car_info_plate = intent.getStringExtra("car_info_plate");
        String package_name = intent.getStringExtra("package_name");
        long clear_time = intent.getLongExtra("clear_time", 0);
        String layer_name = intent.getStringExtra("layer_name");
        String servername = intent.getStringExtra("servername");
        String order_tip = intent.getStringExtra("order_tip");
        String clear_remarks = intent.getStringExtra("clear_remarks");
        String package_count_type = intent.getStringExtra("package_count_type");
        //清洗前照片
        String[] clear_before_img_infos = intent.getStringArrayExtra("clear_before_img_info");
        if (clear_before_img_infos != null) {
            int length = clear_before_img_infos.length;
            switch (length) {
                case 0:
                    break;
                case 1:
                    mClear_before_img1 = clear_before_img_infos[0];
                    Picasso.with(context).load(mClear_before_img1).into(mClearForward1);
                    break;
                case 2:
                    mClear_before_img1 = clear_before_img_infos[0];
                    mClear_before_img2 = clear_before_img_infos[1];
                    Picasso.with(context).load(mClear_before_img1).into(mClearForward1);
                    Picasso.with(context).load(mClear_before_img2).into(mClearForward2);
                    break;
            }
        }
        //清洗后照片
        String[] clear_after_img_infos = intent.getStringArrayExtra("clear_after_img_info");
        if (clear_after_img_infos != null) {
            int length1 = clear_after_img_infos.length;
            switch (length1) {
                case 0:
                    break;
                case 1:
                    mClear_after_img1 = clear_after_img_infos[0];
                    Picasso.with(context).load(mClear_after_img1).into(mClearBack1);
                    break;
                case 2:
                    mClear_after_img1 = clear_after_img_infos[0];
                    mClear_after_img2 = clear_after_img_infos[1];
                    Picasso.with(context).load(mClear_after_img1).into(mClearBack1);
                    Picasso.with(context).load(mClear_after_img2).into(mClearBack2);
                    break;
            }

        }
        mUserName.setText(name + " " + phone);
        mCarNumber.setText(car_info_plate);
        mStopSite.setText(layer_name + car_info_park);
        mStaffRemark.setText(clear_remarks);
        mClearStatus.setText("已清洗");
        mUserRemark.setText(order_tip);
        mClearTime.setText(AppUtils.formatDate(clear_time));
        mProductType.setText(package_name + "/"+package_count_type);
        mClearMan.setText(servername);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_accomplish_detail;
    }


    @OnClick({R.id.title_back, R.id.clear_forward1, R.id.clear_forward2, R.id.clear_back1, R.id.clear_back2, R.id.parentView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.clear_forward1:
                if (mClear_before_img1 != null){
                    Picasso.with(context).load(mClear_after_img1).into(mBigImg);
                    mParentView.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.clear_forward2:
                if (mClear_before_img2 != null){
                    Picasso.with(context).load(mClear_before_img2).into(mBigImg);
                    mParentView.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.clear_back1:
                if (mClear_after_img1 != null){
                    Picasso.with(context).load(mClear_after_img1).into(mBigImg);
                    mParentView.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.clear_back2:
                if (mClear_after_img2 != null){
                    Picasso.with(context).load(mClear_after_img2).into(mBigImg);
                    mParentView.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.parentView:
                mParentView.setVisibility(View.GONE);
                break;
        }
    }


}
