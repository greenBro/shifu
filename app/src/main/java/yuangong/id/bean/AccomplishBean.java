
package yuangong.id.bean;

import java.util.List;

/**
 * Created by Mathy on 2016/9/20 0020.
 * 描述：
 */
public class AccomplishBean {

    private static final String URL_DOMAIN = "http://101.207.227.61/water";
    //private static final String URL_DOMAIN = "http://www.nbinbi.com/new/water";
    /**
     * car_info_park : 1214
     * staff_info : [{"community_info_id":"1","staff_tel":"13659330105","staff_name":"大师兄","staff_type":"1","staff_id":"99","created_time":"1479712163","staff_status":"1","staff_password":""},{"community_info_id":"1","staff_tel":"15089651234","staff_name":"猴子","staff_type":"1","staff_id":"100","created_time":"1479712189","staff_status":"1","staff_password":""}]
     * reserve_time : 1479657600
     * package_count_type : 外饰
     * package_name : 单次内饰
     * order_id : 99
     * order_tip :
     * staff_ids : 100,99
     * reserve_status : 3
     * layer_name : 负二层
     * reserve_info_id : 1000
     * clear_detail_id : 292
     * name : 何先生
     * clear_detail_info : {"clear_after_img_info":[{"created_time":"1479718052","path":"./Public/Clear_After_img/6304e870dedebe48430bdac68d6df3eb.png","clear_after_img_id":"172"},{"created_time":"1479718048","path":"./Public/Clear_After_img/0fd4eeaa3d6788dd5927137c83c85508.png","clear_after_img_id":"171"}],"clear_after_img_ids":"171,172","clear_before_img_ids":"291,292","clear_status":"1","reserve_info_id":"1000","clear_detail_id":"292","clear_remarks":"111111","clear_before_img_info":[{"created_time":"1479718045","path":"./Public/Clear_Before_img/0ac256c2caa8b8dba584cc73d541ffb5.png","clear_before_img_id":"292"},{"created_time":"1479718042","path":"./Public/Clear_Before_img/14e492b64bcedb1a81456c16eecfadd3.png","clear_before_img_id":"291"}],"created_time":"1479718062"}
     * car_info_plate : 川A66666
     * mobile : 18684042362
     */

    private String car_info_park;
    private long reserve_time;
    private String package_count_type;
    private String package_name;
    private String order_id;
    private String order_tip;
    private String staff_ids;
    private int reserve_status;
    private String layer_name;
    private String reserve_info_id;
    private String clear_detail_id;
    private String name;
    /**
     * clear_after_img_info : [{"created_time":"1479718052","path":"./Public/Clear_After_img/6304e870dedebe48430bdac68d6df3eb.png","clear_after_img_id":"172"},{"created_time":"1479718048","path":"./Public/Clear_After_img/0fd4eeaa3d6788dd5927137c83c85508.png","clear_after_img_id":"171"}]
     * clear_after_img_ids : 171,172
     * clear_before_img_ids : 291,292
     * clear_status : 1
     * reserve_info_id : 1000
     * clear_detail_id : 292
     * clear_remarks : 111111
     * clear_before_img_info : [{"created_time":"1479718045","path":"./Public/Clear_Before_img/0ac256c2caa8b8dba584cc73d541ffb5.png","clear_before_img_id":"292"},{"created_time":"1479718042","path":"./Public/Clear_Before_img/14e492b64bcedb1a81456c16eecfadd3.png","clear_before_img_id":"291"}]
     * created_time : 1479718062
     */

    private ClearDetailInfoBean clear_detail_info;
    private String car_info_plate;
    private String mobile;
    /**
     * community_info_id : 1
     * staff_tel : 13659330105
     * staff_name : 大师兄
     * staff_type : 1
     * staff_id : 99
     * created_time : 1479712163
     * staff_status : 1
     * staff_password :
     */

    private List<StaffInfoBean> staff_info;

    public String getCar_info_park() {
        return car_info_park;
    }

    public void setCar_info_park(String car_info_park) {
        this.car_info_park = car_info_park;
    }

    public long getReserve_time() {
        return reserve_time * 1000;
    }

    public void setReserve_time(long reserve_time) {
        this.reserve_time = reserve_time;
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

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_tip() {
        return order_tip;
    }

    public void setOrder_tip(String order_tip) {
        this.order_tip = order_tip;
    }

    public String getStaff_ids() {
        return staff_ids;
    }

    public void setStaff_ids(String staff_ids) {
        this.staff_ids = staff_ids;
    }

    public int  getReserve_status() {
        return reserve_status;
    }

    public void setReserve_status(int reserve_status) {
        this.reserve_status = reserve_status;
    }

    public String getLayer_name() {
        return layer_name;
    }

    public void setLayer_name(String layer_name) {
        this.layer_name = layer_name;
    }

    public String getReserve_info_id() {
        return reserve_info_id;
    }

    public void setReserve_info_id(String reserve_info_id) {
        this.reserve_info_id = reserve_info_id;
    }

    public String getClear_detail_id() {
        return clear_detail_id;
    }

    public void setClear_detail_id(String clear_detail_id) {
        this.clear_detail_id = clear_detail_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClearDetailInfoBean getClear_detail_info() {
        return clear_detail_info;
    }

    public void setClear_detail_info(ClearDetailInfoBean clear_detail_info) {
        this.clear_detail_info = clear_detail_info;
    }

    public String getCar_info_plate() {
        return car_info_plate;
    }

    public void setCar_info_plate(String car_info_plate) {
        this.car_info_plate = car_info_plate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<StaffInfoBean> getStaff_info() {
        return staff_info;
    }

    public void setStaff_info(List<StaffInfoBean> staff_info) {
        this.staff_info = staff_info;
    }

    public static class ClearDetailInfoBean {
        private String clear_after_img_ids;
        private String clear_before_img_ids;
        private String clear_status;
        private String reserve_info_id;
        private String clear_detail_id;
        private String clear_remarks;
        private long created_time;
        /**
         * created_time : 1479718052
         * path : ./Public/Clear_After_img/6304e870dedebe48430bdac68d6df3eb.png
         * clear_after_img_id : 172
         */

        private List<ClearAfterImgInfoBean> clear_after_img_info;
        /**
         * created_time : 1479718045
         * path : ./Public/Clear_Before_img/0ac256c2caa8b8dba584cc73d541ffb5.png
         * clear_before_img_id : 292
         */

        private List<ClearBeforeImgInfoBean> clear_before_img_info;

        public String getClear_after_img_ids() {
            return clear_after_img_ids;
        }

        public void setClear_after_img_ids(String clear_after_img_ids) {
            this.clear_after_img_ids = clear_after_img_ids;
        }

        public String getClear_before_img_ids() {
            return clear_before_img_ids;
        }

        public void setClear_before_img_ids(String clear_before_img_ids) {
            this.clear_before_img_ids = clear_before_img_ids;
        }

        public String getClear_status() {
            return clear_status;
        }

        public void setClear_status(String clear_status) {
            this.clear_status = clear_status;
        }

        public String getReserve_info_id() {
            return reserve_info_id;
        }

        public void setReserve_info_id(String reserve_info_id) {
            this.reserve_info_id = reserve_info_id;
        }

        public String getClear_detail_id() {
            return clear_detail_id;
        }

        public void setClear_detail_id(String clear_detail_id) {
            this.clear_detail_id = clear_detail_id;
        }

        public String getClear_remarks() {
            return clear_remarks;
        }

        public void setClear_remarks(String clear_remarks) {
            this.clear_remarks = clear_remarks;
        }

        public long getCreated_time() {
            return created_time * 1000;
        }

        public void setCreated_time(long created_time) {
            this.created_time = created_time;
        }

        public List<ClearAfterImgInfoBean> getClear_after_img_info() {
            return clear_after_img_info;
        }

        public void setClear_after_img_info(List<ClearAfterImgInfoBean> clear_after_img_info) {
            this.clear_after_img_info = clear_after_img_info;
        }

        public List<ClearBeforeImgInfoBean> getClear_before_img_info() {
            return clear_before_img_info;
        }

        public void setClear_before_img_info(List<ClearBeforeImgInfoBean> clear_before_img_info) {
            this.clear_before_img_info = clear_before_img_info;
        }

        public static class ClearAfterImgInfoBean {
            private String created_time;
            private String path;
            private String clear_after_img_id;

            public String getCreated_time() {
                return created_time;
            }

            public void setCreated_time(String created_time) {
                this.created_time = created_time;
            }

            public String getPath() {
                return URL_DOMAIN +path.substring(1,path.length());
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getClear_after_img_id() {
                return clear_after_img_id;
            }

            public void setClear_after_img_id(String clear_after_img_id) {
                this.clear_after_img_id = clear_after_img_id;
            }
        }

        public static class ClearBeforeImgInfoBean {
            private String created_time;
            private String path;
            private String clear_before_img_id;

            public String getCreated_time() {
                return created_time;
            }

            public void setCreated_time(String created_time) {
                this.created_time = created_time;
            }

            public String getPath() {
                return URL_DOMAIN +path.substring(1,path.length());
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getClear_before_img_id() {
                return clear_before_img_id;
            }

            public void setClear_before_img_id(String clear_before_img_id) {
                this.clear_before_img_id = clear_before_img_id;
            }
        }
    }

    public static class StaffInfoBean {
        private String community_info_id;
        private String staff_tel;
        private String staff_name;
        private String staff_type;
        private String staff_id;
        private String created_time;
        private String staff_status;
        private String staff_password;

        public String getCommunity_info_id() {
            return community_info_id;
        }

        public void setCommunity_info_id(String community_info_id) {
            this.community_info_id = community_info_id;
        }

        public String getStaff_tel() {
            return staff_tel;
        }

        public void setStaff_tel(String staff_tel) {
            this.staff_tel = staff_tel;
        }

        public String getStaff_name() {
            return staff_name;
        }

        public void setStaff_name(String staff_name) {
            this.staff_name = staff_name;
        }

        public String getStaff_type() {
            return staff_type;
        }

        public void setStaff_type(String staff_type) {
            this.staff_type = staff_type;
        }

        public String getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(String staff_id) {
            this.staff_id = staff_id;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public String getStaff_status() {
            return staff_status;
        }

        public void setStaff_status(String staff_status) {
            this.staff_status = staff_status;
        }

        public String getStaff_password() {
            return staff_password;
        }

        public void setStaff_password(String staff_password) {
            this.staff_password = staff_password;
        }
    }



}
