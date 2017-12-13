package yuangong.id.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Mathy on 2016/9/14 0014.
 * 描述：
 */
public class JudgeNetWork {

    public static final int NETWORK_NONE = 0;
    public static final int NETWORK_WIFI = 1;
    public static final int NETWORK_MOBILE = 2;

    public static int getNetWorkState(Context context){
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        // Wifi
        NetworkInfo.State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return NETWORK_WIFI;
        }

        // 手机网络
        state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .getState();
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return NETWORK_MOBILE;
        }

        //无网络
        return NETWORK_NONE;
    }

    /**
     * @return 是否是WiFi状态
     */
    public static boolean isWifi(Context context) {
        int typeMobile = ConnectivityManager.TYPE_WIFI;
        return isWifiOrPhone(context, typeMobile);
    }

    /**
     * @return 是否是手机网络
     */
    public static boolean isPhone(Context context) {
        int typeMobile = ConnectivityManager.TYPE_MOBILE;
        return isWifiOrPhone(context, typeMobile);
    }

    private static boolean isWifiOrPhone(Context context, int typeMobile) {
        //首先判断是否连接上
        if (!isConnected(context))
        //如果没有连接上任何网络，那么，直接返回false，表示连接失败
            return false;

        //获取到网络连接管理器
        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //获取指定状态下的手机网络
        NetworkInfo networkInfo = manager.getNetworkInfo(typeMobile);
        if (networkInfo == null) {
        //如果为null（可能是平板或者是网络极差），直接返回false
            return false;
        }

        //如果俩个条件都满足，那么直接返回true
        return networkInfo.isAvailable() & networkInfo.isConnected();
    }

    /**
     * @return 是否连接上
     */
    public static boolean isConnected(Context context) {
        //通过获取系统的服务，获取到连接的管理器
        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //manager.getActiveNetwork(); 这个方法是SDK 23 才添加的

        //获取到当前可用的网络信息
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();//SDK 1 就有

        //如果没有，那么直接返回false，表示没有可用的网络连接
        if (networkInfo == null) {
            return false;
        }

        //判断，当两个都满足的时候，才返回true，其他的返回false
        return networkInfo.isConnected() & networkInfo.isAvailable();

    }
}
