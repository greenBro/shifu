package yuangong.id.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import yuangong.id.bean.LoginBean;
import yuangong.id.bean.NotAccomplishBean;
import yuangong.id.bean.StaffListBean;


/**
 * Created by Mathy on 2016/8/31 0031.
 */
public class SharePreferenceUtils {
    private static SharedPreferences sharedPreferences;

    private static SharedPreferences getSharedPreferences(Context context, String shareName) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(shareName, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    /**
     * 存储是否第一次进入
     *
     * @param context
     * @param shareName
     */
    public static void saveIsFirstLogin(Context context, String shareName, boolean isfirst) {
        SharedPreferences.Editor editor = getEditor(context, shareName);
        editor.putBoolean("isfirst", isfirst);
        editor.commit();
    }


    /**
     * 获取是否第一次进入
     *
     * @param context
     * @param shareName
     * @return
     */
    public static boolean IsFirstLogin(Context context, String shareName) {
        return getSharedPreferences(context, shareName).getBoolean("isfirst", true);
    }


    public static void getLoginMsg(Context context,String shareName){
        SharedPreferences sharedPreferences = getSharedPreferences(context, shareName);

    }
    /**
     * 存储登录时的信息
     *
     * @param context
     * @param shareName
     * @param password
     */
    public static void saveLoginMsg(Context context, String shareName, String phone, String password,String userinfo) {
        SharedPreferences.Editor edit = getEditor(context, shareName);
        edit.putString("phone",phone);
        edit.putString("password", password);
        edit.putString("result",userinfo);
        edit.commit();
    }


    /**
     * 获取Editor
     *
     * @param context
     * @param shareName
     * @return
     */
    private static SharedPreferences.Editor getEditor(Context context, String shareName) {
        return getSharedPreferences(context, shareName).edit();
    }

    public static void saveLoginStatus(Context context, String shareName, boolean status) {
        SharedPreferences.Editor edit = getEditor(context, shareName);
        edit.putBoolean("loginstatus", status);
        edit.commit();
    }

    public static boolean getLoginStatus(Context context, String shareName) {
        return getSharedPreferences(context, shareName).getBoolean("loginstatus", false);
    }
    /**
     * 存储用户信息
     *
     * @param context
     * @param shareName
     */
    public static void saveUserInfo(Context context, String shareName, String userinfo) {
        SharedPreferences.Editor edit = getEditor(context, shareName);
        edit.putString("result",userinfo);
        edit.commit();
    }


    /**
     *
     * @param context
     * @param shareName
     * @param taskInfo
     */
    public static void saveTaskInfo(Context context,String shareName,String taskInfoName,String taskInfo){
        SharedPreferences.Editor edit = getEditor(context, shareName);
        edit.putString(taskInfoName,taskInfo);
        edit.commit();
    }


    /**
     * 获取CommunityInfoId
     * @return
     */
    public static String getCommunityInfoId(Context context,String shareName){
        String result = getSharedPreferences(context,shareName).getString("result", "");
        LoginBean.ResultBean resultBean = new Gson().fromJson(result, LoginBean.ResultBean.class);
        return resultBean.getCommunity_info_id();
    }
    /**
     * 获取CommunityInfoId
     * @return
     */
    public static String getStaffId(Context context,String shareName){
        String result = getSharedPreferences(context,shareName).getString("result", "");
        LoginBean.ResultBean resultBean = new Gson().fromJson(result, LoginBean.ResultBean.class);
        return resultBean.getStaff_id();
    }

    public static void saveNotAccomplishActivity(Context context,String shareName,String taskInfoName,String taskInfo){
        SharedPreferences.Editor editor = getEditor(context, shareName);
        editor.putString(taskInfoName,taskInfo);
        editor.commit();
    }

    public static List<NotAccomplishBean> getNotAccomplish(Context context,String shareName,String taskInfoName){
        String result = getSharedPreferences(context, shareName).getString(taskInfoName, "");
        Log.i("TGA", result);
        List<NotAccomplishBean> beanList = null;
        try {
            beanList = JSONUtils.getBeanList(result, NotAccomplishBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return beanList;
    }

    public static void saveWashStaffInfo(Context context,String shareName,String taskInfoName,String taskInfo){
        SharedPreferences.Editor editor = getEditor(context, shareName);
        editor.putString(taskInfoName, taskInfo);
        editor.commit();
    }

    public static List<StaffListBean> getWashStaffInfo(Context context,String shareName,String taskInfoName){
        String result = getSharedPreferences(context, shareName).getString(taskInfoName, "");

        List<StaffListBean> beanList = null;
        try {
             JSONObject jsonObject = new JSONObject(result);
             JSONArray jsonArray = jsonObject.optJSONArray("result");
            beanList = JSONUtils.getBeanList(jsonArray, StaffListBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return beanList;
    }

}
