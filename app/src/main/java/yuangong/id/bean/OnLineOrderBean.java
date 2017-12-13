package yuangong.id.bean;

/**
 * Created by Mathy on 2016/9/20 0020.
 * 描述：
 */
public class OnLineOrderBean {

    /**
     * name : 孙先生
     * mobile : 15601732190
     * start_time : 0
     * end_time : 0
     * order_num : 1
     * car_info_plate : 渝C22233
     * car_info_park : 303
     * layer_name : 负一层
     * package_count_type : 手工打蜡
     * package_name : 轿车单次外饰
     * true_money : 0.00
     * created_time : 1478238472
     * order_tip :
     */

    private String name;
    private String mobile;
    private String start_time;
    private String end_time;
    private String order_num;
    private String car_info_plate;
    private String car_info_park;
    private String layer_name;
    private String package_count_type;
    private String package_name;
    private String true_money;
    private long created_time;
    private String order_tip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getCar_info_plate() {
        return car_info_plate;
    }

    public void setCar_info_plate(String car_info_plate) {
        this.car_info_plate = car_info_plate;
    }

    public String getCar_info_park() {
        return car_info_park;
    }

    public void setCar_info_park(String car_info_park) {
        this.car_info_park = car_info_park;
    }

    public String getLayer_name() {
        return layer_name;
    }

    public void setLayer_name(String layer_name) {
        this.layer_name = layer_name;
    }

    public String getPackage_count_type() {
        return package_count_type;
    }

    public void setPackage_count_type(String package_count_type) {
        this.package_count_type = package_count_type;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getTrue_money() {
        return true_money;
    }

    public void setTrue_money(String true_money) {
        this.true_money = true_money;
    }

    public long getCreated_time() {
        return created_time * 1000;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }

    public String getOrder_tip() {
        return order_tip;
    }

    public void setOrder_tip(String order_tip) {
        this.order_tip = order_tip;
    }
}
