package yuangong.id.utils;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杨操 on 2016/9/20.
 * 描述：
 */
public class JSONUtils {
    /**
     * 根据一个字符串 生成一个javaBean
     *
     * @param jsonstr
     * @param cla
     * @param <T>
     * @return
     */
    public static <T> T getBean(String jsonstr, Class<T> cla) throws JSONException {
        return getBean(new JSONObject(jsonstr), cla);
    }

    /**
     * 根据一个jsonBean 生成一个javaBean
     *
     * @param jsonBean
     * @param cla
     * @param <T>
     * @return
     */
    public static <T> T getBean(JSONObject jsonBean, Class<T> cla) {

        Gson g = new Gson();
        T t = null;
        try {
            t = g.fromJson(jsonBean.toString(), cla);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("错误数据" + jsonBean);
        }
        return t;
    }

    /**
     * 根据一个字符串生成对应的javaBean集合
     *
     * @param jsonStr
     * @param cla
     * @param <T>
     * @return
     */
    public static <T> List<T> getBeanList(String jsonStr, Class<T> cla) throws JSONException {
        return getBeanList(new JSONArray(jsonStr), cla);
    }

    /**
     * 更具一个JSONArray生成对应的javaBean集合
     *
     * @param jsonBeanArray
     * @param cla
     * @param <T>
     * @return
     */
    public static <T> List<T> getBeanList(JSONArray jsonBeanArray, Class<T> cla) {
        List<T> list = new ArrayList<T>();
        if (null == jsonBeanArray) {
            return list;
        }
        int length = jsonBeanArray.length();
        System.out.println("解析长度" + length);

        Gson g = new Gson();
        for (int i = 0; i < length; i++) {
            try {
                String aNull = jsonBeanArray.get(i).toString();
                System.out.println(aNull);
                T object = g.fromJson(aNull, cla);
                Log.i("tag", object.toString());
                list.add(object);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("=======>JSONUtils" + e.getMessage().toString());
                System.out.println("出错"+cla);
            }
        }
        return list;
    }

}
