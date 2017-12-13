package yuangong.id.utils;

import android.support.v7.app.AppCompatActivity;

import java.util.LinkedList;

/**
 * Created by Mathy on 2016/9/29 0029.
 * 描述：Activity管理
 */
public class ActivityCollector {

    public static LinkedList<AppCompatActivity> compatActivities = new LinkedList<>();

    /**
     * 添加Activity
     * @param activity
     */
    public static void addActivity(AppCompatActivity activity){
        compatActivities.add(activity);
    }

    /**
     * 移除Activity
     * @param activity
     */
    public static void removeActivity(AppCompatActivity activity){
        compatActivities.remove(activity);
    }

    /**
     * 关闭所有Activity
     */
    public static void finshAll(){
        for (AppCompatActivity activity: compatActivities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
