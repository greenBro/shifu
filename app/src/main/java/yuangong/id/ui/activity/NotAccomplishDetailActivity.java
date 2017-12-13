package yuangong.id.ui.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import yuangong.id.AppConstant;
import yuangong.id.R;
import yuangong.id.bean.ImageBean;
import yuangong.id.bean.StaffListBean;
import yuangong.id.net.NetWorkUtils;
import yuangong.id.ui.activity.base.BaseActivity;
import yuangong.id.utils.AppUtils;
import yuangong.id.utils.BitmapFactoryUtils;
import yuangong.id.utils.DBManager;
import yuangong.id.utils.FileUtil;
import yuangong.id.utils.JSONUtils;
import yuangong.id.utils.JudgeNetWork;
import yuangong.id.utils.SharePreferenceUtils;
import yuangong.id.view.MyGridView;

public class NotAccomplishDetailActivity extends BaseActivity {


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
    @Bind(R.id.order_time)
    TextView mOrderTime;
    @Bind(R.id.clear_status)
    TextView mClearStatus;
    @Bind(R.id.user_remark)
    TextView mUserRemark;
    @Bind(R.id.clear_forward1)
    ImageView mClearForward1;
    @Bind(R.id.clear_forward2)
    ImageView mClearForward2;
    @Bind(R.id.clear_back1)
    ImageView mClearBack1;
    @Bind(R.id.clear_back2)
    ImageView mClearBack2;
    @Bind(R.id.staff_remark)
    EditText mStaffRemark;
    @Bind(R.id.delay_oneday)
    TextView mDelayOneday;
    @Bind(R.id.sure_clear)
    TextView mSureClear;
    @Bind(R.id.vMasker)
    View mVMasker;
    @Bind(R.id.delete_forward1)
    ImageView mDeletaForward1;
    @Bind(R.id.delete_forward2)
    ImageView mDeletaForward2;
    @Bind(R.id.delete_back1)
    ImageView mDeletaAfter1;
    @Bind(R.id.delete_back2)
    ImageView mDeleteAfter2;
    @Bind(R.id.myGridView)
    MyGridView mMyGridView;
    @Bind(R.id.parentView)
    LinearLayout mParentView;
    public static final int REQUEST_CODE_CAMERA1 = 701;
    public static final int REQUEST_CODE_CAMERA2 = 702;
    public static final int REQUEST_CODE_CAMERA3 = 703;
    public static final int REQUEST_CODE_CAMERA4 = 704;
    private static final String dirName = Environment.getExternalStorageDirectory() + "/" + AppConstant.STAFF_FILENAME;
    private int mOrder_id;
    private String mImgBeforePath1 =""; //洗车前照片1地址
    private String mImgBeforePath2 ="";//洗车前照片2地址
    private String mImgAfterPath1="";//洗车后照片1地址
    private String mImgAfterPath2 ="";//洗车后照片2地址
    private String imgBefore1Path = "";  //图片在服务器的地址
    private String imgBefore2Path = "";
    private String imgAfter1Path = "";
    private String imgAfter2Path = "";
    private String imgBefore1Id = "";
    private String imgBefore2Id = "";
    private String imgAfter1Id = "";
    private String imgAfter2Id = "";
    private String mReserve_info_id;
    private AlertDialog mAlertDialog;
    private String mDeyReason;
    private AlertDialog mAlertDialog1;
    public static String TABLE_NAME = "clear_photo_cache";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if (what == 1) {
                AppUtils.setToastMsg(context, "缓存成功");
            } else if (what == 2) {
                AppUtils.setToastMsg(context, "缓存失败");
            } else if (what == 3) {
                AppUtils.setToastMsg(context, "缓存成功");
            }
        }
    };
    private String mStaffId;
    private long mReserve_time;
    private DBManager mDbManager;
    private int mPosition;
    private List<StaffListBean> mStaffListBeans;
    private ArrayAdapter mArrayAdapter;
    private List<String> mWashStaffIds;
    private String mImgAfterIds;
    private String mImgBeforeIds;

    @Override
    protected void initData() {

        mTitleBack.setVisibility(View.VISIBLE);
        mTitleText.setText("未清洗任务详情");
        setMsg();
        mStaffListBeans = new ArrayList<>();
        List<StaffListBean> washStaff = SharePreferenceUtils.getWashStaffInfo(context, AppConstant.USER_SP_NAME, "washStaff");
        if (washStaff != null) {
            mStaffListBeans.addAll(washStaff);
        }
        mArrayAdapter = new ArrayAdapter(context, R.layout.wash_staff_layout, mStaffListBeans) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                final View view = super.getView(position, convertView, parent);
                StaffListBean staffListBean = mStaffListBeans.get(position);
                if (staffListBean.isChoice()) {
                    //view.setBackgroundColor(getResources().getColor(R.color.text_blue));
                    view.setBackgroundResource(R.drawable.shape_gridview_y);
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.white));

                } else {
                    //view.setBackgroundColor(getResources().getColor(R.color.white));
                    view.setBackgroundResource(R.drawable.shape_gridview);
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.black));
                }
                return view;
            }
        };
        mMyGridView.setAdapter(mArrayAdapter);
    }


    /**
     * 添加数据
     * <p>
     * 预约状态 1已预约（未清洗） 2已取消  3洗车工清洗已确认 4用户清洗确认 5已评价
     */
    private void setMsg() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        String name = intent.getStringExtra("username");
        String phone = intent.getStringExtra("phone");
        String car_info_park = intent.getStringExtra("car_info_park");
        String car_info_plate = intent.getStringExtra("car_info_plate");

        String package_name = intent.getStringExtra("package_name");
        String package_count_type = intent.getStringExtra("package_count_type");
        mPosition = intent.getIntExtra("position", 0);
        mReserve_time = intent.getLongExtra("reserve_time", 0);
        String layer_name = intent.getStringExtra("layer_name");
        String order_tip = intent.getStringExtra("order_tip");
        mStaffId = SharePreferenceUtils.getStaffId(context, AppConstant.USER_SP_NAME);
        mReserve_info_id = intent.getStringExtra("reserve_info_id");
        mOrder_id = intent.getIntExtra("order_id", 0);
        /*String clear_before1 = intent.getStringExtra("clear_before1");
        String clear_before2 = intent.getStringExtra("clear_before2");
        String clear_after1 = intent.getStringExtra("clear_after1");
        String clear_after2 = intent.getStringExtra("clear_after2");
        mImgBeforePath2 = intent.getStringExtra("clear_after_ids");
        mImgAfterIds = intent.getStringExtra("clear_before_ids");
        if (!TextUtils.isEmpty(clear_before1)){
            mClearForward1.setImageBitmap(BitmapFactory.decodeFile(clear_before1));
        }
        if (!TextUtils.isEmpty(clear_before2)){
           mClearForward2.setImageBitmap(BitmapFactory.decodeFile(clear_before2));
        }
        if (!TextUtils.isEmpty(clear_after1)){
            mClearBack1.setImageBitmap(BitmapFactory.decodeFile(clear_after1));
        }
        if (!TextUtils.isEmpty(clear_after2)){
            mClearBack2.setImageBitmap(BitmapFactory.decodeFile(clear_after2));
        }*/

        mUserName.setText(name + " " + phone);
        mCarNumber.setText(car_info_plate);
        mStopSite.setText(layer_name + car_info_park);
        mUserRemark.setText(order_tip);
        mOrderTime.setText(AppUtils.formatDate(mReserve_time));
        if (!TextUtils.isEmpty(package_count_type)){
            mProductType.setText(package_name+ "/" + package_count_type);
        }else {
            mProductType.setText(package_name);
        }

    }

    @Override
    protected void setListener() {
        mMyGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StaffListBean staffListBean = mStaffListBeans.get(position);
                staffListBean.setIsChoice(!staffListBean.isChoice());
                if (mWashStaffIds == null) {
                    mWashStaffIds = new ArrayList<String>();
                }
                if (staffListBean.isChoice()) {
                    mWashStaffIds.add(staffListBean.getStaff_id());
                } else {
                    mWashStaffIds.remove(staffListBean.getStaff_id());
                }
                mArrayAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_not_accomplish_detail;
    }


    @OnClick({R.id.title_back, R.id.clear_forward1,
            R.id.clear_forward2, R.id.clear_back1,
            R.id.clear_back2, R.id.delay_oneday,
            R.id.sure_clear, R.id.delete_forward1,
            R.id.delete_forward2, R.id.delete_back1,
            R.id.delete_back2})
    public void onClick(View view) {
        //final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Intent intent = new Intent(context, MultiImageSelectorActivity.class);
        // 最大图片选择数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.clear_forward1:
                startActivityForResult(intent, REQUEST_CODE_CAMERA1);
                break;
            case R.id.clear_forward2:
                startActivityForResult(intent, REQUEST_CODE_CAMERA2);
                break;
            case R.id.clear_back1:
                startActivityForResult(intent, REQUEST_CODE_CAMERA3);
                break;
            case R.id.clear_back2:
                startActivityForResult(intent, REQUEST_CODE_CAMERA4);
                break;
            case R.id.delay_oneday: //延迟一天
                if (!JudgeNetWork.isConnected(context)) {
                    AppUtils.setToastMsg(context, "当前没有网络");
                    return;
                }
                AlertDialog.Builder delayBuilder = new AlertDialog.Builder(context);
                View delayDialog = getLayoutInflater().inflate(R.layout.dialog_clear_delay, null);
                RadioGroup rg = (RadioGroup) delayDialog.findViewById(R.id.dialog_rg);

                final RadioButton dialogRb1 = (RadioButton) delayDialog.findViewById(R.id.dialog_rg1);
                final RadioButton dialogRb2 = (RadioButton) delayDialog.findViewById(R.id.dialog_rg2);
                final RadioButton dialogRb3 = (RadioButton) delayDialog.findViewById(R.id.dialog_rg3);
                TextView quit = (TextView) delayDialog.findViewById(R.id.quit);
                TextView sure = (TextView) delayDialog.findViewById(R.id.sure);

                //其他原因
                final EditText otherReason = (EditText) delayDialog.findViewById(R.id.other_reason);

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        int childCount = group.getChildCount();
                        for (int i = 0; i < childCount; i++) {
                            RadioButton radioButton = (RadioButton) group.getChildAt(i);
                            if (radioButton.isChecked()) {
                                mDeyReason = radioButton.getText().toString();
                                otherReason.setText("");
                            }
                        }
                    }
                });
                otherReason.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String otherReasonMsg = otherReason.getText().toString();
                        if (!TextUtils.isEmpty(otherReasonMsg)) {
                            dialogRb1.setChecked(false);
                            dialogRb2.setChecked(false);
                            dialogRb3.setChecked(false);
                            mDeyReason = otherReasonMsg;
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                delayBuilder.setView(delayDialog);
                mAlertDialog1 = delayBuilder.create();
                mAlertDialog1.show();
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAlertDialog1.dismiss();
                    }
                });

                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delayOnDayRequest(mReserve_info_id, (mReserve_time / 1000 + 24 * 60 * 60), mDeyReason);
                    }
                });

                break;
            case R.id.sure_clear://确认清洗
                //判断是否有网络
                if (mWashStaffIds == null) {
                    Toast.makeText(this, "请选择员工", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String staffIds = getIds();
                System.out.println("=======>staffIds"+staffIds);

                boolean connected = JudgeNetWork.isConnected(context);

                if (connected == true) { //有网
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    final View dialogView = getLayoutInflater().inflate(R.layout.dialog_clear_finish, null);
                    TextView quit2 = (TextView) dialogView.findViewById(R.id.quit);
                    TextView sure2 = (TextView) dialogView.findViewById(R.id.sure);

                    builder.setView(dialogView);
                    mAlertDialog = builder.create();
                    mAlertDialog.show();
                    quit2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mAlertDialog.dismiss();
                        }
                    });

                    sure2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mImgBeforeIds = "";
                            mImgAfterIds = "";
                            //洗车前照片ids
                            if (!TextUtils.isEmpty(imgBefore1Id)) {
                                mImgBeforeIds = mImgBeforeIds + imgBefore1Id;
                            } else {
                                if (!TextUtils.isEmpty(imgBefore2Id)) {
                                    mImgBeforeIds = imgBefore2Id;
                                }
                            }
                            if (!TextUtils.isEmpty(imgBefore2Id)) {
                                mImgBeforeIds = mImgBeforeIds + "," + imgBefore2Id;
                            }
                            //洗车后照片ids
                            if (!TextUtils.isEmpty(imgAfter1Id)) {
                                mImgAfterIds = mImgAfterIds + imgAfter1Id;
                            } else {
                                if (!TextUtils.isEmpty(imgAfter2Id)) {
                                    mImgAfterIds = imgAfter2Id;
                                }
                            }
                            if (!TextUtils.isEmpty(imgAfter2Id)) {
                                mImgAfterIds = mImgAfterIds + "," + imgAfter2Id;
                            }
                            String staffRemark = mStaffRemark.getText().toString();
                            if (TextUtils.isEmpty(staffRemark)) {
                                staffRemark = "";
                            }
                            //添加清洗详情信息网络请求

                            addClearDetailInfo(mReserve_info_id, mImgBeforeIds, mImgAfterIds, staffRemark, staffIds, 3);
                        }
                    });
                } else {//无网络  缓存
                    AlertDialog.Builder noeNetDialog = new AlertDialog.Builder(context);
                    View cacheView = getLayoutInflater().inflate(R.layout.dialog_cache, null);
                    TextView quit2 = (TextView) cacheView.findViewById(R.id.quit);
                    TextView sure2 = (TextView) cacheView.findViewById(R.id.sure);
                    noeNetDialog.setView(cacheView);
                    final AlertDialog alertDialog = noeNetDialog.create();
                    alertDialog.show();
                    quit2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                    sure2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mDbManager == null) {
                                mDbManager = DBManager.getDBManager(context);
                            }
                            final ContentValues values = new ContentValues();
                            values.put("order_id", mOrder_id);
                            values.put("resever_info_id", mReserve_info_id);
                            values.put("before_wash_car1",mImgBeforePath1 );
                            values.put("before_wash_car2", mImgBeforePath2);
                            values.put("after_wash_car1", mImgAfterPath1);
                            values.put("after_wash_car2", mImgAfterPath2);
                            //Log.i("TGA", dirName + "/"+ mFileBeforeName1);
                            values.put("clear_tip", mStaffRemark.getText().toString());
                            values.put("clear_status", 3); //清洗状态 3表示已清洗
                            values.put("is_upload", 0);  //是否上传 0 表示未上传，1表示已上传
                            values.put("staff_ids", staffIds);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Message msg = new Message();
                                    int update = mDbManager.update(TABLE_NAME, values, "resever_info_id = ?", new String[]{mReserve_info_id});

                                    if (update == 0) {
                                        long insert = mDbManager.insert(TABLE_NAME, "0", values);
                                        if (insert > 0) {
                                            msg.what = 1;
                                            mHandler.sendMessage(msg);
                                        } else {
                                            msg.what = 2;
                                            mHandler.sendMessage(msg);
                                        }
                                    } else {
                                        msg.what = 3;
                                        mHandler.sendMessage(msg);
                                    }
                                }
                            }).start();
                            alertDialog.dismiss();
                            setIntent();
                        }
                    });
                }

                break;
            case R.id.delete_forward1: //删除洗车前第一张图片
                mClearForward1.setImageResource(R.mipmap.add_image);
                //System.out.println("======>imgBefore1Path = "+imgBefore1Path);
                deleteImageRequest(AppConstant.DELETE_BEFORE_IMG, 1, imgBefore1Id, null, imgBefore1Path);
                mDeletaForward1.setVisibility(View.GONE);
                break;
            case R.id.delete_forward2:
                mClearForward2.setImageResource(R.mipmap.add_image);
                deleteImageRequest(AppConstant.DELETE_BEFORE_IMG, 1, imgBefore2Id, null, imgBefore2Path);
                mDeletaForward2.setVisibility(View.GONE);
                break;
            case R.id.delete_back1:
                mClearBack1.setImageResource(R.mipmap.add_image);
                deleteImageRequest(AppConstant.DELETE_AFTER_IMG, 2, null, imgAfter1Id, imgAfter1Path);
                mDeletaAfter1.setVisibility(View.GONE);
                break;
            case R.id.delete_back2:
                mClearBack2.setImageResource(R.mipmap.add_image);
                deleteImageRequest(AppConstant.DELETE_AFTER_IMG, 2, null, imgAfter2Id, imgAfter2Path);
                mDeleteAfter2.setVisibility(View.GONE);
                break;

        }
    }

    private void setIntent() {
        Intent intent = new Intent();
        intent.putExtra("position", mPosition);
        /*intent.putExtra("clear_before1",mImgBeforePath1);
        intent.putExtra("clear_before2",mImgBeforePath2);
        intent.putExtra("clear_after1",mImgAfterPath1);
        intent.putExtra("clear_after2",mImgAfterPath2);
        intent.putExtra("clear_before_ids",mImgBeforeIds);
        intent.putExtra("clear_after_ids",mImgAfterIds);*/
        //intent.putExtra("clearStatus",clearStatus); //清洗状态  0未清洗  1已清洗
        setResult(RESULT_OK, intent);
        finish();
    }

    private String getIds() {

        int size = mWashStaffIds.size();
        StringBuffer buffer = new StringBuffer();
        //buffer.append(mStaffId).append(",");
        for (int i = 0; i < size; i++) {
            buffer.append(mWashStaffIds.get(i)).append(",");
        }
        return buffer.toString().substring(0, buffer.length() - 1);
    }

    //延迟一天网络请求
    private void delayOnDayRequest(String reserve_info_id, long new_reserve_time, String delay_reason) {
        if(TextUtils.isEmpty(delay_reason)){
            Toast.makeText(NotAccomplishDetailActivity.this, "请填写延期原因", Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String, Object> params = new HashMap<>();
        params.put("reserve_info_id", reserve_info_id);
        params.put("new_reserve_time", new_reserve_time);
        params.put("delay_reason", delay_reason);
        NetWorkUtils.requestPHP(context, params, AppConstant.DELAY_ONE_DAY, new NetWorkUtils.NetWorkUtilsCallbackPHP() {
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
                mAlertDialog1.dismiss();
            }
        });
    }


    //添加清洗详情信息网络请求
    private void addClearDetailInfo(String reserve_info_id, String clear_before_img_ids,
                                    String clear_after_img_ids, String clear_remarks,
                                    String staff_id, int reserve_status) {
        // System.out.println("======>clear_before_img_ids = " + clear_before_img_ids + " ,clear_after_img_ids = " + clear_after_img_ids);
        final HashMap<String, Object> params = new HashMap<>();
        params.put("reserve_info_id", reserve_info_id);
        if (!TextUtils.isEmpty(clear_before_img_ids)) {
            params.put("clear_before_img_ids", clear_before_img_ids);
        }

        if (!TextUtils.isEmpty(clear_after_img_ids)) {
            params.put("clear_after_img_ids", clear_after_img_ids);
        }
        params.put("clear_remarks", clear_remarks);
        System.out.println("=======>staff_ids = "+staff_id);
       /* if (true){
            return;
        }*/
        params.put("staff_ids", staff_id);
        params.put("reserve_status", reserve_status);

        NetWorkUtils.requestPHP(context, params, AppConstant.ADD_CLEAR_DETAIL_INFO, new NetWorkUtils.NetWorkUtilsCallbackPHP() {
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
                if (mAlertDialog.isShowing()) {
                    mAlertDialog.dismiss();
                }
                setIntent();
            }
        });
    }


    //删除图片网络请求
    private void deleteImageRequest(String url, int type, String clear_before_img_id, String clear_after_img_id, String path) {
        HashMap<String, Object> params = new HashMap<>();
        if (type == 1) {
            params.put("clear_before_img_id", clear_before_img_id);
        } else {
            params.put("clear_after_img_id", clear_after_img_id);
        }
        params.put("path", path);
        NetWorkUtils.requestPHP(context, params, url, new NetWorkUtils.NetWorkUtilsCallbackPHP() {
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
                //String msg = t.optString("msg");
                AppUtils.setToastMsg(context, "删除图片成功");
            }
        });
    }

    //添加图片网络请求
    private void imgRequest(String url, byte[] bitmap, final int number, int type) {
        HashMap<String, Object> params = new HashMap<>();

        int length = bitmap.length;
        String fileSize = FileUtil.getFileSize((long) length);
        System.out.println("======>fileSize = " + fileSize);
        String s = Base64.encodeToString(bitmap, Base64.DEFAULT);
        //System.out.println("=====>" + s);
        if (type == 1) {
            params.put("clear_before_img", s);
        } else {
            params.put("clear_after_img", s);
        }

        NetWorkUtils.requestPHP(context, params, url, new NetWorkUtils.NetWorkUtilsCallbackPHP() {
            @Override
            public void onError(int id) {
                NetWorkUtils.cacheMiss(context, id);
            }

            @Override
            public void onWarn(String msg) {
                //AppUtils.setToastMsg(context, msg);
            }

            @Override
            public void onResponse(JSONObject t) {
                JSONObject jsonObject = t.optJSONObject("result");
                if (jsonObject == null) {
                    AppUtils.setToastMsg(context, "没有数据");
                    return;
                }
                ImageBean bean = JSONUtils.getBean(jsonObject, ImageBean.class);
                switch (number) {
                    case 1:
                        imgBefore1Path = bean.getPath();
                        imgBefore1Id = bean.getId();
                        System.out.println("========>imgBefore1Path = "+ imgBefore1Path);
                        break;
                    case 2:
                        imgBefore2Path = bean.getPath();
                        imgBefore2Id = bean.getId();
                        System.out.println("========>imgBefore2Path = "+ imgBefore2Path);
                        break;
                    case 3:
                        imgAfter1Path = bean.getPath();
                        imgAfter1Id = bean.getId();
                         System.out.println("========>imgAfter1Path = "+ imgAfter1Path);
                        break;
                    case 4:
                        imgAfter2Path = bean.getPath();
                        imgAfter2Id = bean.getId();
                        System.out.println("========>imgAfter2Path = "+ imgAfter2Path);
                        break;
                }
                AppUtils.setToastMsg(context, "图片上传成功");
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //洗车前第2张图
        if (requestCode == REQUEST_CODE_CAMERA1 && resultCode == RESULT_OK) {
            //Bundle bundle = data.getExtras();
            // 获取相机返回的数据，并转换为Bitmap图片格式 ，这是缩略图
            //Bitmap bitmap = (Bitmap) bundle.get("data");
            //mClearForward1.setImageDrawable(new BitmapDrawable(bitmap));
            //mFileBeforeName1 = mReserve_info_id + "before1";
            //BitmapFactoryUtils.saveImage(context, bitmap, AppConstant.STAFF_FILENAME, mFileBeforeName1);
                /*dirName + "/"+ mFileBeforeName1*/
            // 获取返回的图片列表
            List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            mImgBeforePath1 = path.get(0);
            System.out.println("======>mImgBeforePath1" + mImgBeforePath1);
            byte[] buffer = getByteArray(mImgBeforePath1, mClearForward1);
            if (buffer == null){
                AppUtils.setToastMsg(context,"获取图片失败");
                return;
            }
            if (JudgeNetWork.isConnected(context)) {
                imgRequest(AppConstant.URL_CLEAR_BEFORE_IMG, buffer, 1, 1);
            }
            mDeletaForward1.setVisibility(View.VISIBLE);
        }
        //洗车前第2张图
        if (requestCode == REQUEST_CODE_CAMERA2 && resultCode == RESULT_OK) {
            /*Bundle bundle = data.getExtras();
            // 获取相机返回的数据，并转换为Bitmap图片格式 ，这是缩略图
            Bitmap bitmap = (Bitmap) bundle.get("data");
            mClearForward2.setImageDrawable(new BitmapDrawable(bitmap));
            mFileBeforeName2 = mReserve_info_id + "before2";
            BitmapFactoryUtils.saveImage(context, bitmap, AppConstant.STAFF_FILENAME, mFileBeforeName2);
            imgRequest(AppConstant.URL_CLEAR_BEFORE_IMG, dirName + File.separator + mFileBeforeName2, 2, 1);*/
            // 获取返回的图片列表
            List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            mImgBeforePath2 = path.get(0);
            System.out.println("======>mImgBeforePath2" + mImgBeforePath2);
            byte[] byteArray = getByteArray(mImgBeforePath2, mClearForward2);
            if (byteArray == null){
                AppUtils.setToastMsg(context,"获取图片失败");
                return;
            }
            if (JudgeNetWork.isConnected(context)) {
                imgRequest(AppConstant.URL_CLEAR_BEFORE_IMG, byteArray, 2, 1);
            }
            mDeletaForward2.setVisibility(View.VISIBLE);
        }

        //洗车后第1张图片
        if (requestCode == REQUEST_CODE_CAMERA3 && resultCode == RESULT_OK) {
           /* Bundle bundle = data.getExtras();
            // 获取相机返回的数据，并转换为Bitmap图片格式 ，这是缩略图
            Bitmap bitmap = (Bitmap) bundle.get("data");
            mClearBack1.setImageDrawable(new BitmapDrawable(bitmap));
            mFileAfterName1 = mReserve_info_id + "after1";
            BitmapFactoryUtils.saveImage(context, bitmap, AppConstant.STAFF_FILENAME, mFileAfterName1);
            imgRequest(AppConstant.URL_CLEAR_AFTER_IMG, dirName + File.separator + mFileAfterName1, 3, 2);*/
            // 获取返回的图片列表
            List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            mImgAfterPath1 = path.get(0);
            System.out.println("======>mImgAfterPath1" + mImgAfterPath1);
            byte[] byteArray = getByteArray(mImgAfterPath1, mClearBack1);
            if (byteArray == null){
                AppUtils.setToastMsg(context,"获取图片失败");
                return;
            }
            if (JudgeNetWork.isConnected(context)) {
                imgRequest(AppConstant.URL_CLEAR_AFTER_IMG, byteArray, 3, 2);
            }
            mDeletaAfter1.setVisibility(View.VISIBLE);
        }
        //洗车后第2张图片
        if (requestCode == REQUEST_CODE_CAMERA4 && resultCode == RESULT_OK) {
            /*Bundle bundle = data.getExtras();
            // 获取相机返回的数据，并转换为Bitmap图片格式 ，这是缩略图
            Bitmap bitmap = (Bitmap) bundle.get("data");
            mClearBack2.setImageDrawable(new BitmapDrawable(bitmap));
            mFileAfterName2 = mReserve_info_id + "after2";
            BitmapFactoryUtils.saveImage(context, bitmap, AppConstant.STAFF_FILENAME, mFileAfterName2);
            imgRequest(AppConstant.URL_CLEAR_AFTER_IMG, dirName + File.separator + mFileAfterName2, 4, 2);*/
            // 获取返回的图片列表
            List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            mImgAfterPath2 = path.get(0);
            System.out.println("======>mImgAfterPath2" + mImgAfterPath2);
            byte[] byteArray = getByteArray(mImgAfterPath2, mClearBack2);
            if (byteArray == null){
                AppUtils.setToastMsg(context,"获取图片失败");
                return;
            }
            if (JudgeNetWork.isConnected(context)) {
                imgRequest(AppConstant.URL_CLEAR_AFTER_IMG, byteArray, 4, 2);
            }
            mDeleteAfter2.setVisibility(View.VISIBLE);
        }
    }

    @NonNull
    private byte[] getByteArray(String pathName, ImageView imgView) {


        byte[] bytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeFile(pathName);
        int bitmapSize = BitmapFactoryUtils.getBitmapSize(bitmap);
        if(bitmapSize == 0){
            return null;
        }
        if (bitmapSize < 1024 * 500) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            imgView.setImageBitmap(bitmap);
        } else {

            Bitmap smallBitmap = BitmapFactoryUtils.getSmallBitmap(pathName);
            smallBitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos);
            imgView.setImageBitmap(smallBitmap);
        }
        bytes = baos.toByteArray();
        int length = bytes.length;
        String fileSize = FileUtil.getFileSize((long) length);
        System.out.println("======>fileSize = " + fileSize);
        return bytes;
    }

}
