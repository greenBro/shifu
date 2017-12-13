package yuangong.id.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.view.WindowManager;


/**
 * 屏幕控制器
 * 目前功能获取屏幕宽高，并且存入SharedPreferences
 * Created by 杨操 on 2016/3/18.
 */
public class ScreenControl {
    private Context context;
    private WindowManager wm;
    private Point outSize;
    private SharedPreferences sp;
    public ScreenControl(Context context) {
        this.context = context;
        sp=context.getSharedPreferences("screen", Activity.MODE_PRIVATE);
    }

    public int getScreenHigh(){
        int high=0;
             high= sp.getInt("high", 0);
             if(high==0){
                 high=getPoint().y;
                 SharedPreferences.Editor edit = sp.edit();
                 edit.putInt("high",high);
                 edit.commit();
         }

        return high;
    }
    public int getScreenWide(){
        int Wide=0;
            Wide = sp.getInt("Wide", 0);
            if (Wide == 0) {
                Wide = getPoint().x;
                SharedPreferences.Editor edit = sp.edit();
                edit.putInt("Wide", Wide);
                edit.commit();
            }
        return  Wide;
    }
    private WindowManager getWindowManager(Context context){
        if(wm==null&&context!=null){
            wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if(wm==null){
                wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            }
        }
        return wm;
    }
    private Point getPoint(){
        if(outSize==null&&context!=null){
            outSize=new Point();
            WindowManager windowManager = getWindowManager(context);
            if(windowManager!=null){
                windowManager.getDefaultDisplay().getSize(outSize);
            }
        }
        return outSize;
    }
}
