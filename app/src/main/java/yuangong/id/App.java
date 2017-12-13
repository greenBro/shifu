package yuangong.id;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import yuangong.id.utils.JudgeNetWork;

/**
 * Created by Administrator on 2016/8/23.
 */
public class App extends Application {

    private static App app;
    private SharedPreferences sp;
    public static int netWorkState;


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        sp = this.getSharedPreferences("user", Activity.MODE_PRIVATE);
        initData();
    }

    public void setIdentifying(String identifying) {
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("identifying", identifying);
        edit.commit();
    }

    public static App getApp() {
        return app;
    }

    public void initData(){
        netWorkState = JudgeNetWork.getNetWorkState(this);
    }
}
