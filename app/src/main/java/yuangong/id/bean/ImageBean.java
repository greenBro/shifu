package yuangong.id.bean;

import yuangong.id.AppConstant;

/**
 * Created by Mathy on 2016/10/11 0011.
 * 描述：
 */
public class ImageBean {

    /**
     * id : 11
     * path : ./Public/Clear_Before_img/3ecdd603460f275fba4a420f62c5d1fe.png
     */

     private static final String URL_DOMAIN = "http://101.207.227.61/water";
    //private static final String URL_DOMAIN = "http://www.nbinbi.com/new/water";

    private String id;
    private String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
            return URL_DOMAIN +path.substring(1,path.length());
    }

    public void setPath(String path) {
        this.path = path;
    }
}
