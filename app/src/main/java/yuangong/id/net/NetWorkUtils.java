package yuangong.id.net;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Set;

import cz.msebera.android.httpclient.Header;
import yuangong.id.bean.BaseReturnBean;


/**
 * Created by Mathy on 2016/8/18 0018.
 * 网络请求工具类
 */
public class NetWorkUtils {

    private static Gson gson;
    private static  AsyncHttpClient asyncHttpClient;

    private static AsyncHttpClient getAsyncHttpClient() {
        if (asyncHttpClient == null) {
            asyncHttpClient = new AsyncHttpClient();
        }
        return asyncHttpClient;
    }

    /**
     * 纯字符串post上传
     *
     * @param params               key-value{@link Map}值
     * @param url                  接口地址
     * @param cla                  返回的对象class模型
     * @param netWorkUtilsCallback 回调对象
     */
    public static <T> void request(final Context context, Map<String, Object> params, String url, final Class<T> cla, final NetWorkUtilsCallback<T> netWorkUtilsCallback) {
        getAsyncHttpClient().post(context, url, getRequestParams(params, context), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody).replace("null", "");
                System.out.println("responseBody" + response);
                if (netWorkUtilsCallback == null) {
                    return;
                }
                if (cla == String.class) {
                    netWorkUtilsCallback.onResponse((T) response);
                } else {
                    if (gson == null) {
                        gson = new Gson();
                    }
                    try {
                        T t = gson.fromJson(response, cla);
                        if (t instanceof BaseReturnBean) {
                            if (((BaseReturnBean) t).getStatus() == -1) {

                            } else {
                                if (((BaseReturnBean) t).getStatus() == 200) {
                                    netWorkUtilsCallback.onResponse(t);
                                } else {
                                    netWorkUtilsCallback.onWarn(((BaseReturnBean) t).getMsg());
                                }
                            }
                        } else {
                            netWorkUtilsCallback.onWarn("不是BaseReturnBean对象和String对象");
                        }
                    } catch (Exception e) {
                        try {
                            netWorkUtilsCallback.onWarn(new JSONObject(response).getString("msg"));
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (netWorkUtilsCallback != null) {
                    netWorkUtilsCallback.onError(statusCode);
                }
            }
        });
    }

    /**
     * 纯字符串post上传
     * 试用与非面向对象接口的PHP后台
     *
     * @param params                  key-value{@link Map}值
     * @param url                     接口地址
     * @param netWorkUtilsCallbackphp 回调对象
     */
    public static <T> void requestPHP(final Context context, Map<String, Object> params, String url, final NetWorkUtilsCallbackPHP netWorkUtilsCallbackphp) {
        getAsyncHttpClient().post(context, url, getRequestParams(params, context), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody).replace("null", "\"\"");
                System.out.println("responseBody" + response);
                if (netWorkUtilsCallbackphp == null) {
                    return;
                }
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    if ("200".equals(jsonObject.optString("status"))) {
                        netWorkUtilsCallbackphp.onResponse(jsonObject);
                    } else {
                        netWorkUtilsCallbackphp.onWarn(jsonObject.optString("msg"));
                    }

                } catch (JSONException e) {
                    System.out.println("======>" + e.getMessage().toString());
                    netWorkUtilsCallbackphp.onWarn("服务器异常");
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (netWorkUtilsCallbackphp != null) {
                    netWorkUtilsCallbackphp.onError(statusCode);
                }
            }
        });
    }

    private static RequestParams getRequestParams(Map<String, Object> params, Context context) {
        RequestParams requestParams = new RequestParams();
        Set<String> strings = params.keySet();
        for (String value : strings) {
            requestParams.put(value, params.get(value));
            System.out.println("responseBody" + value + params.get(value));
        }
        return requestParams;
    }

    public interface NetWorkUtilsCallback<T> {
        void onError(int id);

        void onWarn(String msg);

        void onResponse(T t);
    }

    public interface NetWorkUtilsCallbackPHP {
        void onError(int id);

        void onWarn(String msg);

        void onResponse(JSONObject t);

    }

    /**
     * 通过不同的返回码显示提示信息
     *
     * @param context  上下文
     * @param missCode 返回码
     */
    public static void cacheMiss(Context context, int missCode) {
        switch (missCode) {
            case 400:
                Toast.makeText(context, "服务器不理解的请求", Toast.LENGTH_SHORT).show();
                break;
            case 401:
                Toast.makeText(context, "未授权的请求", Toast.LENGTH_SHORT).show();
                break;
            case 403:
                Toast.makeText(context, "服务器拒绝了请求", Toast.LENGTH_SHORT).show();
                break;
            case 404:
                Toast.makeText(context, "找不到的请求", Toast.LENGTH_SHORT).show();
                break;
            case 405:
                Toast.makeText(context, "请求中指定的方法被禁用了", Toast.LENGTH_SHORT).show();
                break;
            case 406:
                Toast.makeText(context, "服务器没有接受此次请求", Toast.LENGTH_SHORT).show();
                break;
            case 407:
                Toast.makeText(context, "此次请求需要代理授权", Toast.LENGTH_SHORT).show();
                break;
            case 408:
                Toast.makeText(context, "请求超时", Toast.LENGTH_SHORT).show();
                break;
            case 409:
                Toast.makeText(context, "此次请求产生了冲突", Toast.LENGTH_SHORT).show();
                break;
            case 410:
                Toast.makeText(context, "请求的资源不存在", Toast.LENGTH_SHORT).show();
                break;
            case 411:
                Toast.makeText(context, "请求的类容无效", Toast.LENGTH_SHORT).show();
                break;
            case 413:
                Toast.makeText(context, "请求实体过大", Toast.LENGTH_SHORT).show();
                break;
            case 414:
                Toast.makeText(context, "请求的URI过长", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, "网络访问失败", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    //public static void
}
