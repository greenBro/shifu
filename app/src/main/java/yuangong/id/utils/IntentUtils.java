package yuangong.id.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Mathy on 2016/9/13 0013.
 * 描述：跳转工具类
 */
public class IntentUtils {

    public static void startActivity(Context context, Class<?> cls) {
        context.startActivity(new Intent(context, cls));
    }

    public static void startActivity(Context context, Class<?> cls, int type) {
        Intent intent = new Intent(context, cls);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }
}
