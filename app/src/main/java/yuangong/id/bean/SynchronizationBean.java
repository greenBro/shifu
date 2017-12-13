package yuangong.id.bean;

import android.text.TextUtils;

/**
 * Created by 杨操 on 2016/10/29 0029.
 * 描述：用于同步数据的JavaBean
 */
public class  SynchronizationBean {
    private String  staff_id;
    private String resever_info_id;
    private String imgBeforeId;
    private String imgAfterId;
    private String clear_tip;
    private String clear_status;
    private String order_id;
    private String  beforeImg1;
    private String  beforeImg2;
    private String  afterImg1;
    private String  afterImg2;

    public SynchronizationBean(String staff_id, String resever_info_id,
                               String clear_tip, String clear_status,
                               String order_id, String beforeImg1, String beforeImg2,
                               String afterImg1, String afterImg2) {
        this.staff_id = staff_id;
        this.resever_info_id = resever_info_id;
        this.clear_tip = clear_tip;
        this.clear_status = clear_status;
        this.order_id = order_id;
        this.beforeImg1 = beforeImg1;
        this.beforeImg2 = beforeImg2;
        this.afterImg1 = afterImg1;
        this.afterImg2 = afterImg2;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public String getResever_info_id() {
        return resever_info_id;
    }

    public String getImgBeforeId() {
        imgBeforeId="";
        if (!TextUtils.isEmpty(beforeImg1)){
            imgBeforeId = imgBeforeId +  beforeImg1;
        }
        if (!TextUtils.isEmpty(beforeImg2)){
            if (!TextUtils.isEmpty(beforeImg1)){
                imgBeforeId = imgBeforeId + "," + beforeImg2;
            } else {
                imgBeforeId =beforeImg2;
            }
        }

        return imgBeforeId;
    }

    public String getImgAfterId() {
        imgAfterId="";
        if (!TextUtils.isEmpty(afterImg1)){
            imgAfterId = imgAfterId +  afterImg1;
        }
        if (!TextUtils.isEmpty(afterImg2)){
            if (!TextUtils.isEmpty(afterImg1)){
                imgAfterId = imgAfterId + "," + afterImg2;
            } else {
                imgAfterId =afterImg2;
            }
        }

        return imgAfterId;

    }

    public String getClear_tip() {
        return clear_tip;
    }

    public String getClear_status() {
        return clear_status;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getBeforeImg1() {
        return beforeImg1;
    }

    public String getBeforeImg2() {
        return beforeImg2;
    }

    public String getAfterImg1() {
        return afterImg1;
    }

    public String getAfterImg2() {
        return afterImg2;
    }

    public void setBeforeImg1(String beforeImg1) {
        this.beforeImg1 = beforeImg1;
    }

    public void setBeforeImg2(String beforeImg2) {
        this.beforeImg2 = beforeImg2;
    }

    public void setAfterImg1(String afterImg1) {
        this.afterImg1 = afterImg1;
    }

    public void setAfterImg2(String afterImg2) {
        this.afterImg2 = afterImg2;
    }
}
