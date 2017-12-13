package yuangong.id.bean;

import java.util.List;

/**
 * Created by Mathy on 2016/9/18 0018.
 * 描述：
 */
public class ActivityBean {


    /**
     * activity_id : 1
     * activity_description : 现在消费满500元立减50元
     * activity_name : 【最新活动】购买商品1月送1张美容优惠卷...
     * activity_condition_ids : 1,2
     * activity_starttime : 0
     * activity_endtime : 2147483647
     * created_time : 4294967295
     * activity_type : 2
     * activity_status : 1
     * activity_condition_info : [{"activity_condition_id":"1","community_info_id":"0","package_ids":"3","activity_rewards_id":"1","package_num":"0","package_totalprice":"2000","activity_condition_start_time":"2147483647","activity_condition_end_time":"0","created_time":"0","package_info_assemble":[{"package_id":"3","car_type_id":"1","community_info_id":"2","package_count_ids":"3","package_image_ids":"3","package_name":"抛光打蜡","package_discribe":"就是这么闪这么闪","package_price":"998","package_paynum":"0","package_month":"1","package_detail":"","created_time":"54654654","start_time":"0","end_time":"0","package_type":"3","package_status":"1","package_info":{"package_id":"3","car_type_id":"1","community_info_id":"2","package_count_ids":"3","package_image_ids":"3","package_name":"抛光打蜡","package_discribe":"就是这么闪这么闪","package_price":"998","package_paynum":"0","package_month":"1","package_detail":"","created_time":"54654654","start_time":"0","end_time":"0","package_type":"3","package_status":"1","package_count":[{"package_count_id":"3","package_count_type_id":"4","package_count_num":"1","created_time":"32132131","package_count_type":[{"package_count_type_id":"4","package_count_type":"抛光打蜡","created_time":"4294967295"}]}],"package_image":[{"package_image_id":"3","package_imagepath":"./Public/Uploads/Package/2016-09-13/57d7696b135ee.jpg","created_time":"1473735019"}]}}],"activity_rewards_info":{"activity_rewards_id":"1","activity_discount_id":"0","coupon_id":"0","activity_money_id":"1","package_id":"0","activity_num":"1","activity_money_info":{"activity_money_id":"1","activity_money":"0"}}},{"activity_condition_id":"2","community_info_id":"0","package_ids":"0","activity_rewards_id":"3","package_num":"3","package_totalprice":"0","activity_condition_start_time":"0","activity_condition_end_time":"1573510926","created_time":"0","activity_rewards_info":{"activity_rewards_id":"3","activity_discount_id":"1","coupon_id":"0","activity_money_id":"0","package_id":"0","activity_num":"1","activity_discount_info":{"activity_discount_id":"1","activity_discount":"0.9"}}}]
     */

    private String activity_id;
    private String activity_description;
    private String activity_name;
    private String activity_condition_ids;
    private long activity_starttime;
    private long activity_endtime;
    private String created_time;
    private String activity_type;
    private String activity_status;
    /**
     * activity_condition_id : 1
     * community_info_id : 0
     * package_ids : 3
     * activity_rewards_id : 1
     * package_num : 0
     * package_totalprice : 2000
     * activity_condition_start_time : 2147483647
     * activity_condition_end_time : 0
     * created_time : 0
     * package_info_assemble : [{"package_id":"3","car_type_id":"1","community_info_id":"2","package_count_ids":"3","package_image_ids":"3","package_name":"抛光打蜡","package_discribe":"就是这么闪这么闪","package_price":"998","package_paynum":"0","package_month":"1","package_detail":"","created_time":"54654654","start_time":"0","end_time":"0","package_type":"3","package_status":"1","package_info":{"package_id":"3","car_type_id":"1","community_info_id":"2","package_count_ids":"3","package_image_ids":"3","package_name":"抛光打蜡","package_discribe":"就是这么闪这么闪","package_price":"998","package_paynum":"0","package_month":"1","package_detail":"","created_time":"54654654","start_time":"0","end_time":"0","package_type":"3","package_status":"1","package_count":[{"package_count_id":"3","package_count_type_id":"4","package_count_num":"1","created_time":"32132131","package_count_type":[{"package_count_type_id":"4","package_count_type":"抛光打蜡","created_time":"4294967295"}]}],"package_image":[{"package_image_id":"3","package_imagepath":"./Public/Uploads/Package/2016-09-13/57d7696b135ee.jpg","created_time":"1473735019"}]}}]
     * activity_rewards_info : {"activity_rewards_id":"1","activity_discount_id":"0","coupon_id":"0","activity_money_id":"1","package_id":"0","activity_num":"1","activity_money_info":{"activity_money_id":"1","activity_money":"0"}}
     */

    private List<ActivityConditionInfoBean> activity_condition_info;

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getActivity_description() {
        return activity_description;
    }

    public void setActivity_description(String activity_description) {
        this.activity_description = activity_description;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_condition_ids() {
        return activity_condition_ids;
    }

    public void setActivity_condition_ids(String activity_condition_ids) {
        this.activity_condition_ids = activity_condition_ids;
    }

    public long getActivity_starttime() {
        return activity_starttime * 1000;
    }

    public void setActivity_starttime(long activity_starttime) {
        this.activity_starttime = activity_starttime;
    }

    public long getActivity_endtime() {
        return activity_endtime * 1000;
    }

    public void setActivity_endtime(long activity_endtime) {
        this.activity_endtime = activity_endtime;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public String getActivity_status() {
        return activity_status;
    }

    public void setActivity_status(String activity_status) {
        this.activity_status = activity_status;
    }

    public List<ActivityConditionInfoBean> getActivity_condition_info() {
        return activity_condition_info;
    }

    public void setActivity_condition_info(List<ActivityConditionInfoBean> activity_condition_info) {
        this.activity_condition_info = activity_condition_info;
    }

    public static class ActivityConditionInfoBean {
        private String activity_condition_id;
        private String community_info_id;
        private String package_ids;
        private String activity_rewards_id;
        private String package_num;
        private String package_totalprice;
        private String activity_condition_start_time;
        private String activity_condition_end_time;
        private String created_time;
        /**
         * activity_rewards_id : 1
         * activity_discount_id : 0
         * coupon_id : 0
         * activity_money_id : 1
         * package_id : 0
         * activity_num : 1
         * activity_money_info : {"activity_money_id":"1","activity_money":"0"}
         */

        private ActivityRewardsInfoBean activity_rewards_info;
        /**
         * package_id : 3
         * car_type_id : 1
         * community_info_id : 2
         * package_count_ids : 3
         * package_image_ids : 3
         * package_name : 抛光打蜡
         * package_discribe : 就是这么闪这么闪
         * package_price : 998
         * package_paynum : 0
         * package_month : 1
         * package_detail :
         * created_time : 54654654
         * start_time : 0
         * end_time : 0
         * package_type : 3
         * package_status : 1
         * package_info : {"package_id":"3","car_type_id":"1","community_info_id":"2","package_count_ids":"3","package_image_ids":"3","package_name":"抛光打蜡","package_discribe":"就是这么闪这么闪","package_price":"998","package_paynum":"0","package_month":"1","package_detail":"","created_time":"54654654","start_time":"0","end_time":"0","package_type":"3","package_status":"1","package_count":[{"package_count_id":"3","package_count_type_id":"4","package_count_num":"1","created_time":"32132131","package_count_type":[{"package_count_type_id":"4","package_count_type":"抛光打蜡","created_time":"4294967295"}]}],"package_image":[{"package_image_id":"3","package_imagepath":"./Public/Uploads/Package/2016-09-13/57d7696b135ee.jpg","created_time":"1473735019"}]}
         */

        private List<PackageInfoAssembleBean> package_info_assemble;

        public String getActivity_condition_id() {
            return activity_condition_id;
        }

        public void setActivity_condition_id(String activity_condition_id) {
            this.activity_condition_id = activity_condition_id;
        }

        public String getCommunity_info_id() {
            return community_info_id;
        }

        public void setCommunity_info_id(String community_info_id) {
            this.community_info_id = community_info_id;
        }

        public String getPackage_ids() {
            return package_ids;
        }

        public void setPackage_ids(String package_ids) {
            this.package_ids = package_ids;
        }

        public String getActivity_rewards_id() {
            return activity_rewards_id;
        }

        public void setActivity_rewards_id(String activity_rewards_id) {
            this.activity_rewards_id = activity_rewards_id;
        }

        public String getPackage_num() {
            return package_num;
        }

        public void setPackage_num(String package_num) {
            this.package_num = package_num;
        }

        public String getPackage_totalprice() {
            return package_totalprice;
        }

        public void setPackage_totalprice(String package_totalprice) {
            this.package_totalprice = package_totalprice;
        }

        public String getActivity_condition_start_time() {
            return activity_condition_start_time;
        }

        public void setActivity_condition_start_time(String activity_condition_start_time) {
            this.activity_condition_start_time = activity_condition_start_time;
        }

        public String getActivity_condition_end_time() {
            return activity_condition_end_time;
        }

        public void setActivity_condition_end_time(String activity_condition_end_time) {
            this.activity_condition_end_time = activity_condition_end_time;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public ActivityRewardsInfoBean getActivity_rewards_info() {
            return activity_rewards_info;
        }

        public void setActivity_rewards_info(ActivityRewardsInfoBean activity_rewards_info) {
            this.activity_rewards_info = activity_rewards_info;
        }

        public List<PackageInfoAssembleBean> getPackage_info_assemble() {
            return package_info_assemble;
        }

        public void setPackage_info_assemble(List<PackageInfoAssembleBean> package_info_assemble) {
            this.package_info_assemble = package_info_assemble;
        }

        public static class ActivityRewardsInfoBean {
            private String activity_rewards_id;
            private String activity_discount_id;
            private String coupon_id;
            private String activity_money_id;
            private String package_id;
            private String activity_num;
            /**
             * activity_money_id : 1
             * activity_money : 0
             */

            private ActivityMoneyInfoBean activity_money_info;

            public String getActivity_rewards_id() {
                return activity_rewards_id;
            }

            public void setActivity_rewards_id(String activity_rewards_id) {
                this.activity_rewards_id = activity_rewards_id;
            }

            public String getActivity_discount_id() {
                return activity_discount_id;
            }

            public void setActivity_discount_id(String activity_discount_id) {
                this.activity_discount_id = activity_discount_id;
            }

            public String getCoupon_id() {
                return coupon_id;
            }

            public void setCoupon_id(String coupon_id) {
                this.coupon_id = coupon_id;
            }

            public String getActivity_money_id() {
                return activity_money_id;
            }

            public void setActivity_money_id(String activity_money_id) {
                this.activity_money_id = activity_money_id;
            }

            public String getPackage_id() {
                return package_id;
            }

            public void setPackage_id(String package_id) {
                this.package_id = package_id;
            }

            public String getActivity_num() {
                return activity_num;
            }

            public void setActivity_num(String activity_num) {
                this.activity_num = activity_num;
            }

            public ActivityMoneyInfoBean getActivity_money_info() {
                return activity_money_info;
            }

            public void setActivity_money_info(ActivityMoneyInfoBean activity_money_info) {
                this.activity_money_info = activity_money_info;
            }

            public static class ActivityMoneyInfoBean {
                private String activity_money_id;
                private String activity_money;

                public String getActivity_money_id() {
                    return activity_money_id;
                }

                public void setActivity_money_id(String activity_money_id) {
                    this.activity_money_id = activity_money_id;
                }

                public String getActivity_money() {
                    return activity_money;
                }

                public void setActivity_money(String activity_money) {
                    this.activity_money = activity_money;
                }
            }
        }

        public static class PackageInfoAssembleBean {
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
            private String package_detail;
            private String created_time;
            private String start_time;
            private String end_time;
            private String package_type;
            private String package_status;
            /**
             * package_id : 3
             * car_type_id : 1
             * community_info_id : 2
             * package_count_ids : 3
             * package_image_ids : 3
             * package_name : 抛光打蜡
             * package_discribe : 就是这么闪这么闪
             * package_price : 998
             * package_paynum : 0
             * package_month : 1
             * package_detail :
             * created_time : 54654654
             * start_time : 0
             * end_time : 0
             * package_type : 3
             * package_status : 1
             * package_count : [{"package_count_id":"3","package_count_type_id":"4","package_count_num":"1","created_time":"32132131","package_count_type":[{"package_count_type_id":"4","package_count_type":"抛光打蜡","created_time":"4294967295"}]}]
             * package_image : [{"package_image_id":"3","package_imagepath":"./Public/Uploads/Package/2016-09-13/57d7696b135ee.jpg","created_time":"1473735019"}]
             */

            private PackageInfoBean package_info;

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

            public String getPackage_detail() {
                return package_detail;
            }

            public void setPackage_detail(String package_detail) {
                this.package_detail = package_detail;
            }

            public String getCreated_time() {
                return created_time;
            }

            public void setCreated_time(String created_time) {
                this.created_time = created_time;
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

            public PackageInfoBean getPackage_info() {
                return package_info;
            }

            public void setPackage_info(PackageInfoBean package_info) {
                this.package_info = package_info;
            }

            public static class PackageInfoBean {
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
                private String package_detail;
                private String created_time;
                private String start_time;
                private String end_time;
                private String package_type;
                private String package_status;
                /**
                 * package_count_id : 3
                 * package_count_type_id : 4
                 * package_count_num : 1
                 * created_time : 32132131
                 * package_count_type : [{"package_count_type_id":"4","package_count_type":"抛光打蜡","created_time":"4294967295"}]
                 */

                private List<PackageCountBean> package_count;
                /**
                 * package_image_id : 3
                 * package_imagepath : ./Public/Uploads/Package/2016-09-13/57d7696b135ee.jpg
                 * created_time : 1473735019
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

                public String getPackage_detail() {
                    return package_detail;
                }

                public void setPackage_detail(String package_detail) {
                    this.package_detail = package_detail;
                }

                public String getCreated_time() {
                    return created_time;
                }

                public void setCreated_time(String created_time) {
                    this.created_time = created_time;
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
                     * package_count_type_id : 4
                     * package_count_type : 抛光打蜡
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
        }
    }
}
