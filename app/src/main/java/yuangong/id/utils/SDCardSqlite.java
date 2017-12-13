package yuangong.id.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

/**
 * Created by Mathy on 2016/10/13 0013.
 * 描述：
 */
public class SDCardSqlite extends SQLiteOpenHelper {

    /**
     * 数据库名称
     */
    private static String DA_NAME = Environment.getExternalStorageDirectory().getAbsolutePath()+"/waterstaff"+"/staffDb";
    //private static String DA_NAME = "staffDb";
    /**
     * 数据库版本
     **/
    public static int DB_VERSION = 2;


    public SDCardSqlite(Context context) {
        super(context, DA_NAME, null, DB_VERSION);
        //System.out.println(DA_NAME);
    }

    /**
     * "CREATE TABLE clear_photo_cache\n" +
     "(order_id int(11) primary key,\n" + //订单号
     "resever_info_id int(11),\n" +        //服务id
     "before_wash_car1 varchar(255),\n" +   //洗车前第一张照片
     "before_wash_car2 varchar(255),\n" +   //洗车前第二张照片
     "after_wash_car1 varchar(255),\n" +    //洗车后第一张照片
     "after_wash_car2 varchar(255),\n" +    //洗车后第二张照片
     "clear_tip varchar(255),\n" +          //洗车工备注
     "clear_status int(10),\n" +            //清洗状态  3
     "is_upload int(10),\n"+                // 是否上传  0 未上传，1上传
     "staff_id int(11));"                   //员工id
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE clear_photo_cache\n" +
                "(order_id int(11),\n" +
                "resever_info_id varchar(255) primary key,\n" +
                "before_wash_car1 varchar(255),\n" +
                "staff_ids varchar(255),\n" +
                "before_wash_car2 varchar(255),\n" +
                "after_wash_car1 varchar(255),\n" +
                "after_wash_car2 varchar(255),\n" +
                "clear_tip varchar(255),\n" +
                "clear_status int(10),\n" +
                "is_upload int(10),\n"+
                "staff_id int(11));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlupdatacocle_Version_2="ALTER TABLE clear_photo_cache ADD COLUMN staff_ids BLOB varchar(255)";
        if (oldVersion == 1 && newVersion == 2){
            db.execSQL(sqlupdatacocle_Version_2);
        }
    }
}
