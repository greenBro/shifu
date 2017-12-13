package yuangong.id.ui.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import yuangong.id.App;
import yuangong.id.utils.JudgeNetWork;

/**
 * Created by Mathy on 2016/9/14 0014.
 * 描述：
 */
public class NetWorkBroadCastReceiver extends BroadcastReceiver {

    public static ArrayList<netEventHandler> mListeners = new ArrayList<netEventHandler>();
    private static String NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(NET_CHANGE_ACTION)) {
            App.netWorkState = JudgeNetWork.getNetWorkState(context);
            if (mListeners.size() > 0)// 通知接口完成加载
            {
                mListeners.get(0).onNetChange();
            }
               /* for (netEventHandler handler : mListeners) {
                    handler.onNetChange();
                }*/
        }
    }

    public static abstract interface netEventHandler {

        public abstract void onNetChange();
    }
}
