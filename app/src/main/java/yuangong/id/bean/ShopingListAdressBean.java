package yuangong.id.bean;

import java.util.List;

/**
 * Created by 杨操 on 2016/9/12.
 * 描述：
 */
public class ShopingListAdressBean extends BaseReturnBean{


    /**
     * community_info_id : 2
     * community_layer_info_ids : [{"community_layer_info_id":"1","layer_name":"负一层"},{"community_layer_info_id":"2","layer_name":"负二层"}]
     * community_info_name : 疯人院
     * community_info_province : 陕西
     * community_info_city : 西安
     * community_info_conty : 禁区
     * created_time : 646549874
     */

    private List<ResultEntity> result;

    public List<ResultEntity> getResult() {
        return result;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public static class ResultEntity {
        private String community_info_id;
        private String community_info_name;
        private String community_info_province;
        private String community_info_city;
        private String community_info_conty;
        private String created_time;
        /**
         * community_layer_info_id : 1
         * layer_name : 负一层
         */

        private List<CommunityLayerInfoIdsEntity> community_layer_info_ids;

        public String getCommunity_info_id() {
            return community_info_id;
        }

        public void setCommunity_info_id(String community_info_id) {
            this.community_info_id = community_info_id;
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

        public List<CommunityLayerInfoIdsEntity> getCommunity_layer_info_ids() {
            return community_layer_info_ids;
        }

        public void setCommunity_layer_info_ids(List<CommunityLayerInfoIdsEntity> community_layer_info_ids) {
            this.community_layer_info_ids = community_layer_info_ids;
        }

        public static class CommunityLayerInfoIdsEntity {
            private String community_layer_info_id;
            private String layer_name;

            public String getCommunity_layer_info_id() {
                return community_layer_info_id;
            }

            public void setCommunity_layer_info_id(String community_layer_info_id) {
                this.community_layer_info_id = community_layer_info_id;
            }

            public String getLayer_name() {
                return layer_name;
            }

            public void setLayer_name(String layer_name) {
                this.layer_name = layer_name;
            }
        }
    }
}
