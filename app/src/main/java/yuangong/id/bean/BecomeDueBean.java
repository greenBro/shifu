package yuangong.id.bean;

/**
 * Created by Mathy on 2016/9/20 0020.
 * 描述：
 */
public class BecomeDueBean {

    /**
     * name : 地狱咆哮先生
     * mobile : 13659330105
     * start_time : 0
     * end_time : 1477411200
     * order_num : 1
     * car_info_plate : 川A765LU
     * car_info_park : 07
     * layer_name : 负一层
     * package_count_type : 普洗
     */

    private String name;
    private String mobile;
    private long start_time;
    private long end_time;
    private String order_num;
    private String car_info_plate;
    private String car_info_park;
    private String layer_name;
    private String package_count_type;

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

    public long getStart_time() {
        return start_time * 1000;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getEnd_time() {
        return end_time * 1000;
    }

    public void setEnd_time(long end_time) {
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
}
