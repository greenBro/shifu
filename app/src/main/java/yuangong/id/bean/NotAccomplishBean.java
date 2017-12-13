package yuangong.id.bean;

/**
 * Created by Mathy on 2016/9/19 0019.
 * 描述：
 */
public class NotAccomplishBean {

    private boolean isClear = false;

    public int getReserve_type() {
        return reserve_type;
    }

    public void setReserve_type(int reserve_type) {
        this.reserve_type = reserve_type;
    }

    public boolean isClear() {
        return isClear;
    }

    /**
     * order_id : 114
     * reserve_info_id : 959
     * package_name : 单次内饰
     * name : 瓜袜子先生
     * mobile : 15325639896
     * car_info_plate : 川a89659
     * car_info_park : 2栋80
     * layer_name : 负二层
     * package_count_type : 外饰
     * reserve_time : 1479744000
     * reserve_status : 1
     * order_tip : 12
     */

    private int reserve_type;
    private String order_id;
    private String reserve_info_id;
    private String package_name;
    private String name;
    private String mobile;
    private String car_info_plate;
    private String car_info_park;
    private String layer_name;
    private String package_count_type;
    private long reserve_time;
    private String reserve_status;
    private String order_tip;
    private String reserve_info_tip;

    public String getReserve_info_tip() {
        return reserve_info_tip;
    }

    public void setReserve_info_tip(String reserve_info_tip) {
        this.reserve_info_tip = reserve_info_tip;
    }

    public boolean getIsClear() {
        return isClear;
    }

    public void setIsClear(boolean isClear) {
        this.isClear = isClear;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getReserve_info_id() {
        return reserve_info_id;
    }

    public void setReserve_info_id(String reserve_info_id) {
        this.reserve_info_id = reserve_info_id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

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

    public long getReserve_time() {
        return reserve_time * 1000;
    }

    public void setReserve_time(long reserve_time) {
        this.reserve_time = reserve_time;
    }

    public String getReserve_status() {
        return reserve_status;
    }

    public void setReserve_status(String reserve_status) {
        this.reserve_status = reserve_status;
    }

    public String getOrder_tip() {
        return order_tip;
    }

    public void setOrder_tip(String order_tip) {
        this.order_tip = order_tip;
    }
}
