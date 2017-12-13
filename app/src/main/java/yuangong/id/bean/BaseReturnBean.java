package yuangong.id.bean;

/**
 * Created by 杨操 on 2016/8/30.
 * 描述：接口返回javaBean的基础Bean
 */
public class BaseReturnBean {

    /**
     * status : 200  状态码
     * isSucess : true  请求状态描述布尔值
     * msg : 登录成功   提示信息
     * "result": {}  返回体
     */

    private int status;
    private boolean isSucess;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isIsSucess() {
        return isSucess;
    }

    public void setIsSucess(boolean isSucess) {
        this.isSucess = isSucess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
