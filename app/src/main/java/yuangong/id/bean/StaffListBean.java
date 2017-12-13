package yuangong.id.bean;

/**
 * Created by Mathy on 2016/9/22 0022.
 * 描述：
 */
public class StaffListBean {


    private String staff_id;
    private String community_info_id;
    private String staff_name;
    private String staff_password;
    private String staff_tel;
    private String staff_type;
    private String staff_status;
    private String created_time;
    private boolean isChoice = false;

    public boolean isChoice() {
        return isChoice;
    }

    public void setIsChoice(boolean isChoice) {
        this.isChoice = isChoice;
    }

    private CommunityInfoBean community_info;

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getCommunity_info_id() {
        return community_info_id;
    }

    public void setCommunity_info_id(String community_info_id) {
        this.community_info_id = community_info_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getStaff_password() {
        return staff_password;
    }

    public void setStaff_password(String staff_password) {
        this.staff_password = staff_password;
    }

    public String getStaff_tel() {
        return staff_tel;
    }

    public void setStaff_tel(String staff_tel) {
        this.staff_tel = staff_tel;
    }

    public String getStaff_type() {
        return staff_type;
    }

    public void setStaff_type(String staff_type) {
        this.staff_type = staff_type;
    }

    public String getStaff_status() {
        return staff_status;
    }

    public void setStaff_status(String staff_status) {
        this.staff_status = staff_status;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public CommunityInfoBean getCommunity_info() {
        return community_info;
    }

    public void setCommunity_info(CommunityInfoBean community_info) {
        this.community_info = community_info;
    }

    public static class CommunityInfoBean {
        private String community_info_id;
        private String staff_leader_id;
        private String staff_ids;
        private String community_layer_info_ids;
        private String community_info_name;
        private String community_info_province;
        private String community_info_city;
        private String community_info_conty;
        private String created_time;

        public String getCommunity_info_id() {
            return community_info_id;
        }

        public void setCommunity_info_id(String community_info_id) {
            this.community_info_id = community_info_id;
        }

        public String getStaff_leader_id() {
            return staff_leader_id;
        }

        public void setStaff_leader_id(String staff_leader_id) {
            this.staff_leader_id = staff_leader_id;
        }

        public String getStaff_ids() {
            return staff_ids;
        }

        public void setStaff_ids(String staff_ids) {
            this.staff_ids = staff_ids;
        }

        public String getCommunity_layer_info_ids() {
            return community_layer_info_ids;
        }

        public void setCommunity_layer_info_ids(String community_layer_info_ids) {
            this.community_layer_info_ids = community_layer_info_ids;
        }

        public String getCommunity_info_name() {
            return community_info_name;
        }

        public void setCommunity_info_name(String community_info_name) {
            this.community_info_name = community_info_name;
        }

        public String getCommunity_info_province() {
            return community_info_province;
        }

        public void setCommunity_info_province(String community_info_province) {
            this.community_info_province = community_info_province;
        }

        public String getCommunity_info_city() {
            return community_info_city;
        }

        public void setCommunity_info_city(String community_info_city) {
            this.community_info_city = community_info_city;
        }

        public String getCommunity_info_conty() {
            return community_info_conty;
        }

        public void setCommunity_info_conty(String community_info_conty) {
            this.community_info_conty = community_info_conty;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }
    }

    @Override
    public String toString() {
        return  staff_name;
    }
}
