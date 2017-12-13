package yuangong.id.ui.activity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpRequest;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import yuangong.id.AppConstant;
import yuangong.id.bean.ImageBean;
import yuangong.id.bean.LoginBean;
import yuangong.id.bean.SynchronizationBean;
import yuangong.id.net.NetWorkUtils;
import yuangong.id.ui.activity.base.BaseActivity;
import yuangong.id.utils.AppUtils;
import yuangong.id.utils.BitmapFactoryUtils;
import yuangong.id.utils.DBManager;
import yuangong.id.utils.FileUtil;
import yuangong.id.utils.IntentUtils;
import yuangong.id.R;
import yuangong.id.utils.JSONUtils;
import yuangong.id.utils.JudgeNetWork;
import yuangong.id.utils.SharePreferenceUtils;
import yuangong.id.view.MyGridView;

/**
 * Created by Administrator on 2016/8/23.
 */
public class MainActivity extends BaseActivity {


    @Bind(R.id.not_accomplish_task_text)
    TextView mNotAccomplishTaskText;
    @Bind(R.id.not_accomplish_task)
    LinearLayout mNotAccomplishTask;
    @Bind(R.id.yes_accomplish_task_text)
    TextView mYesAccomplishTaskText;
    @Bind(R.id.yes_accomplish_task)
    LinearLayout mYesAccomplishTask;
    @Bind(R.id.accomplish_task_text)
    TextView mAccomplishTaskText;
    @Bind(R.id.accomplish_task)
    LinearLayout mAccomplishTask;
    @Bind(R.id.title_text)
    TextView mTitleText;
    @Bind(R.id.title_right_text)
    ToggleButton mTitleRightText;
    @Bind(R.id.image_ad)
    RelativeLayout mImageAd;
    @Bind(R.id.ll1)
    LinearLayout mLl1;
    @Bind(R.id.cut_line)
    TextView mCutLine;
    @Bind(R.id.gridview_menu)
    MyGridView mGridviewMenu;
    @Bind(R.id.setting)
    ImageView mSetting;
    @Bind(R.id.staff_info)
    TextView mStaffInfo;
    @Bind(R.id.parentView)
    LinearLayout mParentView;
    private String[] a = {"资费详情", "在线订单", "优惠活动", "团队管理", "到期提醒", "离线同步"};
    private Integer[] b = {R.mipmap.detail_icon, R.mipmap.useradd_icon, R.mipmap.messege_icon,
            R.mipmap.team_icon, R.mipmap.tixin_icon, R.mipmap.tongbu_icon};
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;


    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    private String mImgBefore1Id;
    private String mImgBefore2Id;
    private String mImgAfter1Id;
    private String mImgAfter2Id;
    private String mCommunity_info_id;
    private boolean firstIn = true;
    private int mSynchroNum = 0;//同步的条数
    private ProgressDialog pbarDialog;
    private int mNum;

    @Override
    protected void initData() {
        mTitleText.setText("微特尔负责人端");
        SharedPreferences sharedPre = getSharedPreferences(AppConstant.USER_SP_NAME, MODE_PRIVATE);
        String result = sharedPre.getString("result", "");
        LoginBean.ResultBean resultBean = new Gson().fromJson(result, LoginBean.ResultBean.class);
        mCommunity_info_id = resultBean.getCommunity_info_id();
        mStaffInfo.setText(resultBean.getCommunity_info().getCommunity_info_name() + "\n" + resultBean.getStaff_name());
        setView();
        //网络请求
        request(mCommunity_info_id);
     /*  new AsyncHttpClient().get(this, "http://www.nbinbi.com/up.html", new AsyncHttpResponseHandler() {
           @Override
           public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
               System.out.println("======>enw " + new String(responseBody));
           }

           @Override
           public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

           }
       });*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        mNum = 0;
        DBManager dbManager = DBManager.getDBManager(this);
        Cursor cursor = dbManager.query(true, NotAccomplishDetailActivity.TABLE_NAME, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int is_upload = cursor.getInt(cursor.getColumnIndex("is_upload"));
            if (is_upload == 0) {
                mNum++;
            }
        }
        cursor.close();
        if (mNum != 0) {
            AppUtils.setToastMsg(context, "有" + mNum + "条数据没同步,请立即同步");
        } else {
            AppUtils.setToastMsg(context, "当前没有需要同步的数据");
        }

        if (firstIn == false) {
            if (JudgeNetWork.isConnected(context)) {
                request(mCommunity_info_id);
            } else {
                AppUtils.setToastMsg(context, "当前没有可用网络，刷新失败");
            }
        } else {
            firstIn = false;
        }

    }

    /**
     * 获取未清洗，已清洗，两者总和数量
     */
    private void request(String id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("community_info_id", id);
        NetWorkUtils.requestPHP(context, params, AppConstant.CLEAR_NUMBER, new NetWorkUtils.NetWorkUtilsCallbackPHP() {
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
                try {
                    JSONObject result = t.getJSONObject("result");
                    int clearingCount = result.optInt("ClearingCount");
                    mNotAccomplishTaskText.setText(clearingCount + "");
                    int clearedCount = result.optInt("ClearedCount");
                    mYesAccomplishTaskText.setText(clearedCount + "");
                    int clearTotolCount = result.optInt("ClearTotolCount");
                    mAccomplishTaskText.setText(clearTotolCount + "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    private void setView() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;
        for (int i = 0; i < 6; i++) {
            map = new HashMap<>();
            map.put("name", a[i]);
            map.put("image", b[i]);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.item_gradview,
                new String[]{"name", "image"},
                new int[]{R.id.textview, R.id.imageview});
        mGridviewMenu.setAdapter(adapter);
        mGridviewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0://跳转到资费详情
                        IntentUtils.startActivity(MainActivity.this, PostageActivity.class);
                        break;
                    case 1://在线订单
                        IntentUtils.startActivity(MainActivity.this, OnLineOrderActivity.class);
                        break;
                    case 2://优惠活动
                        IntentUtils.startActivity(MainActivity.this, PreferentialActivity.class);
                        break;
                    case 3://团队管理
                        IntentUtils.startActivity(MainActivity.this, TeamManageActivity.class);
                        break;
                    case 4://到期提醒
                        IntentUtils.startActivity(MainActivity.this, BecomeDueNotifyActivity.class);
                        break;
                    case 5://离线同步
                        //AppUtils.setToastMsg(context, "离线同步成功1");

                        if (!JudgeNetWork.isConnected(context)) {
                            AppUtils.setToastMsg(context, "当前没有可用网络");
                            return;
                        }

                        pbarDialog = new ProgressDialog(context);
                        pbarDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        pbarDialog.setTitle("请不要轻易关闭");
                        pbarDialog.setMessage("同步进度...");
                        pbarDialog.setMax(mNum);
                        if (mNum > 0) {
                            pbarDialog.show();
                        }


                        DBManager dbManager = DBManager.getDBManager(MainActivity.this);
                        Cursor cursor = dbManager.query(true, NotAccomplishDetailActivity.TABLE_NAME, null, null, null, null, null);

                        while (cursor.moveToNext()) {
                            int order_id = cursor.getInt(cursor.getColumnIndex("order_id"));
                            String beforeImg1 = cursor.getString(cursor.getColumnIndex("before_wash_car1"));
                            String beforeImg2 = cursor.getString(cursor.getColumnIndex("before_wash_car2"));
                            String afterImg1 = cursor.getString(cursor.getColumnIndex("after_wash_car1"));
                            String afterImg2 = cursor.getString(cursor.getColumnIndex("after_wash_car2"));
                            String resever_info_id = cursor.getString(cursor.getColumnIndex("resever_info_id"));
                            int clear_status = cursor.getInt(cursor.getColumnIndex("clear_status"));
                            String clear_tip = cursor.getString(cursor.getColumnIndex("clear_tip"));
                            int is_upload = cursor.getInt(cursor.getColumnIndex("is_upload"));
                            String staff_ids = cursor.getString(cursor.getColumnIndex("staff_ids"));

                            if (is_upload == 0) {
                                Map<Integer, String> stringMap = new HashMap<Integer, String>();
                                stringMap.put(1, beforeImg1);
                                stringMap.put(2, beforeImg2);
                                stringMap.put(3, afterImg1);
                                stringMap.put(4, afterImg2);
                                SynchronizationBean synchronizationBean = new SynchronizationBean(staff_ids, resever_info_id, clear_tip,
                                        clear_status + "", order_id + "", "", "", "", "");
                                imgRequest(AppConstant.URL_CLEAR_BEFORE_IMG, stringMap, 1, 1, synchronizationBean);

                            }

                        }
                        cursor.close();
                        break;
                }
            }
        });
    }

    //添加清洗详情信息网络请求
    private void addClearDetailInfo(final SynchronizationBean synchronizationBean) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("reserve_info_id", synchronizationBean.getResever_info_id());
        params.put("clear_before_img_ids", synchronizationBean.getImgBeforeId());
        params.put("clear_after_img_ids", synchronizationBean.getImgAfterId());
        params.put("clear_remarks", synchronizationBean.getClear_tip());
        String staff_id = synchronizationBean.getStaff_id();
        System.out.println("=======>staff_ids = " + staff_id);
        params.put("staff_ids", staff_id);
        params.put("reserve_status", synchronizationBean.getClear_status());

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
                //AppUtils.setToastMsg(context, t.optString("msg"));

                mSynchroNum++;
                AppUtils.setToastMsg(context, "已同步" + mSynchroNum + "条数据");
                int value = pbarDialog.getProgress() + 1;
                pbarDialog.setProgress(value);
                if (mNum == value) {
                    if (pbarDialog.isShowing()) {
                        pbarDialog.dismiss();
                    }
                    mSynchroNum = 0;
                    mNum = 0;
                }
                ContentValues values = new ContentValues();
                values.put("is_upload", 1);
                DBManager dbManager = DBManager.getDBManager(MainActivity.this);
                dbManager.update(NotAccomplishDetailActivity.TABLE_NAME, values, "resever_info_id = ?", new String[]{synchronizationBean.getResever_info_id()});
            }
        });
    }

    @NonNull
    private byte[] getByteArray(String pathName) {

        byte[] bytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeFile(pathName);
        int bitmapSize = BitmapFactoryUtils.getBitmapSize(bitmap);
        if (bitmapSize == 0){
            return bytes;
        }
        if (bitmapSize < 1024 * 500) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        } else {
            Bitmap smallBitmap = BitmapFactoryUtils.getSmallBitmap(pathName);
            smallBitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos);
            /*while (BitmapFactoryUtils.getBitmapSize(smallBitmap) > 1024 * 500) {
                smallBitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos);
            }*/
        }

        bytes = baos.toByteArray();
        int length = bytes.length;
        String fileSize = FileUtil.getFileSize((long) length);
        System.out.println("======>fileSize1111 = " + fileSize);
        return bytes;
    }

    //添加图片网络请求
    private void imgRequest(String url, final Map<Integer, String> imgFileName, final int number, int type,
                            final SynchronizationBean synchronizationBean) {
        String s1 = imgFileName.get(number);
        System.out.println("===========number"+number);
        if (TextUtils.isEmpty(s1)) {
            System.out.println("===========number"+number+"s1"+s1);
            recursionS(number, imgFileName, synchronizationBean);
            return;
        }

        HashMap<String, Object> params = new HashMap<>();
        System.out.println("=======>s1" + s1);

        byte[] buffer = getByteArray(s1);
        if (buffer == null){
            AppUtils.setToastMsg(context,"获取图片失败");
            return;
        }
        String s = Base64.encodeToString(buffer, Base64.DEFAULT);
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
                recursionS(number , imgFileName, synchronizationBean);
            }

            @Override
            public void onWarn(String msg) {
                //AppUtils.setToastMsg(context, msg);
                recursionS(number, imgFileName, synchronizationBean);
            }

            @Override
            public void onResponse(JSONObject t) {
                JSONObject jsonObject = t.optJSONObject("result");
                if (jsonObject == null) {
                    AppUtils.setToastMsg(context, "没有数据");
                    return;
                }
                ImageBean bean = JSONUtils.getBean(jsonObject, ImageBean.class);
                //AppUtils.setToastMsg(context, t.optString("msg"));

                switch (number) {
                    case 1:
                        mImgBefore1Id = bean.getId();
                        synchronizationBean.setBeforeImg1(mImgBefore1Id);
                        break;
                    case 2:
                        mImgBefore2Id = bean.getId();
                        synchronizationBean.setBeforeImg2(mImgBefore2Id);
                        break;
                    case 3:
                        mImgAfter1Id = bean.getId();
                        synchronizationBean.setAfterImg1(mImgAfter1Id);
                        break;
                    case 4:
                        mImgAfter2Id = bean.getId();
                        synchronizationBean.setAfterImg2(mImgAfter2Id);
                        break;
                }
                recursionS(number, imgFileName, synchronizationBean);
            }
        });
    }

    private void recursionS(int number, Map<Integer, String> imgFileName, SynchronizationBean synchronizationBean) {
        switch (number) {
            case 1:
                imgRequest(AppConstant.URL_CLEAR_BEFORE_IMG, imgFileName, 2, 1, synchronizationBean);
                break;
            case 2:
                imgRequest(AppConstant.URL_CLEAR_AFTER_IMG, imgFileName, 3, 2, synchronizationBean);
                break;
            case 3:
                imgRequest(AppConstant.URL_CLEAR_AFTER_IMG, imgFileName, 4, 2, synchronizationBean);
                break;
            case 4:
                addClearDetailInfo(synchronizationBean);
                break;
        }
    }


    @OnClick({R.id.staff_info, R.id.setting, R.id.not_accomplish_task, R.id.yes_accomplish_task, R.id.accomplish_task})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting:
                IntentUtils.startActivity(MainActivity.this, SettingActivity.class);
                break;
            case R.id.not_accomplish_task:
                IntentUtils.startActivity(context, NotAccomplishActivity.class);
                break;
            case R.id.yes_accomplish_task:
                IntentUtils.startActivity(context, AccomplishActivity.class, 1);
                break;
            case R.id.accomplish_task:
                IntentUtils.startActivity(context, AccomplishActivity.class, 2);
                break;
            case R.id.staff_info:

                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }


}
