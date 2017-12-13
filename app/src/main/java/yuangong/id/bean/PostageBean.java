package yuangong.id.bean;

import java.util.List;

/**
 * Created by Mathy on 2016/10/10 0010.
 * 描述：
 */
public class PostageBean {

    /**
     * package_id : 1
     * car_type_id : 0
     * community_info_id : 1
     * package_count_ids : 1
     * package_image_ids : 1
     * package_name : 118元/包月套餐
     * package_discribe : 测试
     * package_price : 118
     * package_paynum : 0
     * package_month : 1
     * start_time : 0
     * end_time : 4294967295
     * created_time : 32141511
     * package_type : 1
     * package_status : 1
     * car_type : []
     * package_count : [{"package_count_id":"1","package_count_type_id":"1","package_count_num":"6","created_time":"312321312","package_count_type":[{"package_count_type_id":"1","package_count_type":"普洗","created_time":"4294967295"}]}]
     * package_image : [{"package_image_id":"1","package_imagepath":"1475216602649.jpe","created_time":"1475216602"}]
     */

    private String package_id;
    private String car_type_id;
    private String community_info_id;
    private String package_count_ids;
    private String package_image_ids;
    private String package_name;
    private String package_discribe;
    private String package_price;
    private String package_paynum;
    private String package_month;
    private String start_time;
    private String end_time;
    private String created_time;
    private String package_type;
    private String package_status;
    private CarTypeBean car_type;
    /**
     * package_count_id : 1
     * package_count_type_id : 1
     * package_count_num : 6
     * created_time : 312321312
     * package_count_type : [{"package_count_type_id":"1","package_count_type":"普洗","created_time":"4294967295"}]
     */

    private List<PackageCountBean> package_count;
    /**
     * package_image_id : 1
     * package_imagepath : 1475216602649.jpe
     * created_time : 1475216602
     */

    private List<PackageImageBean> package_image;

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    public String getCar_type_id() {
        return car_type_id;
    }

    public void setCar_type_id(String car_type_id) {
        this.car_type_id = car_type_id;
    }

    public String getCommunity_info_id() {
        return community_info_id;
    }

    public void setCommunity_info_id(String community_info_id) {
        this.community_info_id = community_info_id;
    }

    public String getPackage_count_ids() {
        return package_count_ids;
    }

    public void setPackage_count_ids(String package_count_ids) {
        this.package_count_ids = package_count_ids;
    }

    public String getPackage_image_ids() {
        return package_image_ids;
    }

    public void setPackage_image_ids(String package_image_ids) {
        this.package_image_ids = package_image_ids;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPackage_discribe() {
        return package_discribe;
    }

    public void setPackage_discribe(String package_discribe) {
        this.package_discribe = package_discribe;
    }

    public String getPackage_price() {
        return package_price;
    }

    public void setPackage_price(String package_price) {
        this.package_price = package_price;
    }

    public String getPackage_paynum() {
        return package_paynum;
    }

    public void setPackage_paynum(String package_paynum) {
        this.package_paynum = package_paynum;
    }

    public String getPackage_month() {
        return package_month;
    }

    public void setPackage_month(String package_month) {
        this.package_month = package_month;
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

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getPackage_type() {
        return package_type;
    }

    public void setPackage_type(String package_type) {
        this.package_type = package_type;
    }

    public String getPackage_status() {
        return package_status;
    }

    public void setPackage_status(String package_status) {
        this.package_status = package_status;
    }

    public CarTypeBean getCar_type() {
        return car_type;
    }

    public void setCar_type(CarTypeBean car_type) {
        this.car_type = car_type;
    }

    public List<PackageCountBean> getPackage_count() {
        return package_count;
    }

    public void setPackage_count(List<PackageCountBean> package_count) {
        this.package_count = package_count;
    }

    public List<PackageImageBean> getPackage_image() {
        return package_image;
    }

    public void setPackage_image(List<PackageImageBean> package_image) {
        this.package_image = package_image;
    }

    public static class PackageCountBean {
        private String package_count_id;
        private String package_count_type_id;
        private String package_count_num;
        private String created_time;
        /**
         * package_count_type_id : 1
         * package_count_type : 普洗
         * created_time : 4294967295
         */

        private List<PackageCountTypeBean> package_count_type;

        public String getPackage_count_id() {
            return package_count_id;
        }

        public void setPackage_count_id(String package_count_id) {
            this.package_count_id = package_count_id;
        }

        public String getPackage_count_type_id() {
            return package_count_type_id;
        }

        public void setPackage_count_type_id(String package_count_type_id) {
            this.package_count_type_id = package_count_type_id;
        }

        public String getPackage_count_num() {
            return package_count_num;
        }

        public void setPackage_count_num(String package_count_num) {
            this.package_count_num = package_count_num;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public List<PackageCountTypeBean> getPackage_count_type() {
            return package_count_type;
        }

        public void setPackage_count_type(List<PackageCountTypeBean> package_count_type) {
            this.package_count_type = package_count_type;
        }

        public static class PackageCountTypeBean {
            private String package_count_type_id;
            private String package_count_type;
            private String created_time;

            public String getPackage_count_type_id() {
                return package_count_type_id;
            }

            public void setPackage_count_type_id(String package_count_type_id) {
                this.package_count_type_id = package_count_type_id;
            }

            public String getPackage_count_type() {
                return package_count_type;
            }

            public void setPackage_count_type(String package_count_type) {
                this.package_count_type = package_count_type;
            }

            public String getCreated_time() {
                return created_time;
            }

            public void setCreated_time(String created_time) {
                this.created_time = created_time;
            }
        }
    }

    public static class PackageImageBean {
        private String package_image_id;
        private String package_imagepath;
        private String created_time;

        public String getPackage_image_id() {
            return package_image_id;
        }

        public void setPackage_image_id(String package_image_id) {
            this.package_image_id = package_image_id;
        }

        public String getPackage_imagepath() {
            return package_imagepath;
        }

        public void setPackage_imagepath(String package_imagepath) {
            this.package_imagepath = package_imagepath;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }
    }
}
