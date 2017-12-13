package yuangong.id;

/**
 * Created by Administrator on 2016/8/23.
 */
public class AppConstant {


    private static final String URL_DOMAIN = "http://101.207.227.61/";//测试服
   // private static final String URL_DOMAIN = "http://www.nbinbi.com/new/";

    public static final String USER_SP_NAME = "userinfo";
    public static final String STAFF_FILENAME = "waterstaff";

    /**
     * 登录接口
     */
    public static final String URL_LOGIN = URL_DOMAIN + "water/index.php/Staff/Login/checkUsername";

    /**
     * 优惠活动接口
     */
    public static final String URL_ACTIVITY = URL_DOMAIN + "water/index.php/Staff/Activity/getAllActivityList";

    /**
     * 关于我们
     */
    public static final String ABOUT_US = URL_DOMAIN + "water/index.php/Staff/Article/getAboutWater";

    /**
     * 获取未清洗，已清洗，两者总和数量接口
     */
    public static final String CLEAR_NUMBER = URL_DOMAIN + "water/index.php/Staff/ReserveInfo/ClearCount";

    /**
     * 未清洗列表
     */
    public static final String URL_NOT_ACCOMPLISH = URL_DOMAIN + "water/index.php/Staff/ReserveInfo/ClearingList";

    /**
     * 已清洗列表
     */
    public static final String URL_ACCOMPLISH = URL_DOMAIN + "water/index.php/Staff/ReserveInfo/ClearedList";
    /**
     * 所有清洗列表
     */
    public static final String URL_ALL_ACCOMPLISH = URL_DOMAIN + "water/index.php/Staff/ReserveInfo/ClearTotolList";
    /**
     * 在线订单
     */
    public static final String All_ONLINE_ORDER_LIST = URL_DOMAIN + "water/index.php/Staff/Order/getAllOrderList";

    /**
     * 到期提醒
     */
    public static final String URL_BECOMEDUE_NOTIFY = URL_DOMAIN + "water/index.php/Staff/Order/getAllRemindOrderList";
    /**
     * 修改密码
     */
    public static final String URL_CHANGE_PASSWORD = URL_DOMAIN + "water/index.php/Staff/Staff/UpdateStaffPassword";
    /**
     * 员工列表
     */
    public static final String URL_STAFF_LIST = URL_DOMAIN + "water/index.php/Staff/Staff/getStaffListByCommunityInfoId";
    /**
     * 添加员工
     */
    public static final String URL_ADD_STAFF = URL_DOMAIN + "water/index.php/Staff/Staff/AddStaffInfo";
    /**
     * 删除单个员工
     */
    public static final String URL_DELETE_STAFF = URL_DOMAIN + "water/index.php/Staff/Staff/DeleteStaffInfo";
    /**
     * 转移员工
     */
    public static final String URL_TRANSFER_STAFF = URL_DOMAIN + "water/index.php/Staff/Staff/UpdateStaffCommunityInfo";
    /**
     * 洗车前照片
     */
    public static final String URL_CLEAR_BEFORE_IMG = URL_DOMAIN + "water/index.php/Staff/ClearImg/addClearBeforeImg";
    /**
     * 洗车后照片
     */
    public static final String URL_CLEAR_AFTER_IMG = URL_DOMAIN + "water/index.php/Staff/ClearImg/addClearAfterImg";
    /**
     *获取汽车类型
     */
    public static final String URL_GET_CAR_TYPE = URL_DOMAIN + "water/index.php/Staff/CarType/getCarTypeList";
    /**
     * 获取地址列表
     */
    public static final String GET_ADDRESS_DATA = URL_DOMAIN + "/water/index.php/Staff/CommunityInfo/CommunityList";
    /**
     * 小区资费
     */
    public static final String GET_POSTAGE_DATA = URL_DOMAIN + "water/index.php/Staff/Package/PackageListByComunimityId";
    /**
     * 删除洗车前的照片
     */
    public static final String DELETE_BEFORE_IMG = URL_DOMAIN + "water/index.php/Staff/ClearImg/DelClearBeforeImg";
    /**
     * 删除洗车后的照片
     */
    public static final String DELETE_AFTER_IMG = URL_DOMAIN + "water/index.php/Staff/ClearImg/DelClearAfterImg";
    /**
     * 添加清洗详情信息
     */
    public static final String ADD_CLEAR_DETAIL_INFO = URL_DOMAIN + "water/index.php/Staff/ClearDetail/AddClearDetailInfo";
    /**
     * 延迟一天
     */
    public static final String DELAY_ONE_DAY = URL_DOMAIN + "water/index.php/Staff/ReserveInfo/DelayOneDay";
    /**
     * 获取userid
     */
    public static final String USER_ID_BY_PARAM = URL_DOMAIN + "water/index.php/Staff/User/getUserIdByParam";


}
