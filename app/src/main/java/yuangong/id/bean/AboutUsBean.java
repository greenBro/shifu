package yuangong.id.bean;

/**
 * Created by Mathy on 2016/9/18 0018.
 * 描述：
 */
public class AboutUsBean extends BaseReturnBean {


    /**
     * id : 5
     * title : 关于微特尔
     * content : 这是一条假数据这是一条假数据这是一条假数据这是一条假数据这是一条假数据
     * order : 12
     * create_time : 1231231233
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String id;
        private String title;
        private String content;
        private String order;
        private String create_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
