package yuangong.id.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import yuangong.id.R;
import yuangong.id.utils.AppUtils;


/**
 * Created by 杨操 on性别选择 2016/8/29.
 * 描述：创建一个PopupWindow
 */
public class ChoicePicturePopupWindow {
    private GenderSelectListener genderSelectListener;
    private Activity context;


    public ChoicePicturePopupWindow(Activity context) {
        this.context = context;
    }

    public PopupWindow CreatGenderPopupwindow() {
        PopupWindow popupWindow=new PopupWindow(context){
            @Override
            public void dismiss() {

                super.dismiss();
                WindowManager.LayoutParams params=context.getWindow().getAttributes();
                params.alpha=1.0f;
                context.getWindow().setAttributes(params);
            }
        };

        popupWindow.setContentView(buildView(popupWindow));
        popupWindow.setWidth(LinearLayoutCompat.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ffffff")));
        return popupWindow;
    }

    public void setGenderSelectListener(GenderSelectListener genderSelectListener) {
        this.genderSelectListener = genderSelectListener;
    }

    public interface GenderSelectListener {
        /**
         * 选择性别后的回调函数1-男；2-女
         *
         * @param a
         */
        void back(int a);
    }

    /**
     * 构建视图设置监听
     * @param popupWindow
     * @return
     */
    private View buildView(final PopupWindow popupWindow){
        LinearLayout contentView = new LinearLayout(context);
        contentView.setOrientation(LinearLayout.VERTICAL);
        //TODO 创建男女选择,相应按钮控件添加进容器
        TextView camera=new TextView(context);
        TextView picture=new TextView(context);
        TextView button=new TextView(context);
        contentView.addView(camera);
        contentView.addView(picture);
        contentView.addView(button);
        camera.setText("打开照相机");
        camera.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        camera.setGravity(Gravity.CENTER);
        picture.setText("打开相册");
        picture.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        picture.setGravity(Gravity.CENTER);

        //TODO 设置背景
        Drawable drawable = context.getResources().getDrawable(R.drawable.shape_popwind);
        camera.setBackgroundDrawable(drawable);
        picture.setBackgroundDrawable(drawable);
        // TODO 设置控件间距，布局样式
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        int i = AppUtils.dip2px(context, 12);
        int i1 = AppUtils.dip2px(context, 11);
        params.setMargins(i, 0, i, i1);
        camera.setLayoutParams(params);
        camera.setPadding(0, i1, 0, i1);
        picture.setLayoutParams(params);
        picture.setPadding(0, i1, 0, i1);
        //TODO 设置 相应按钮
        button.setText("取消");
        button.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        button.setGravity(Gravity.CENTER);
        int color = context.getResources().getColor(R.color.white);
        button.setBackgroundColor(color);
        button.setPadding(0, i1, 0, i1);
        //TODO 设置监听
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (genderSelectListener != null) {
                    genderSelectListener.back(1);
                }

                popupWindow.dismiss();
            }
        });
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (genderSelectListener != null) {
                    genderSelectListener.back(2);
                }
                popupWindow.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        return contentView;
    }
}
