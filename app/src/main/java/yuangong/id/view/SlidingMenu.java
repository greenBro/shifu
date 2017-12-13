package yuangong.id.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import yuangong.id.R;


/**
 * Created by Mathy on 2016/8/26 0026.
 * 自定义侧滑删除菜单
 */
public class SlidingMenu extends HorizontalScrollView {

    private LinearLayout mWapper;
    private ViewGroup mContent;
    private View mMenu;

    private int mMenuWidth;
    private int mScreenWidth;

    private boolean once = false;
    private boolean isOpen = false;

    private onOpenChangeListener mListener;

    public SlidingMenu(Context context) {
        this(context,null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义属性
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlidingMenu,defStyleAttr,0);
        //获取自定义属性的个数
        int indexCount = array.getIndexCount();

        for (int i = 0; i < indexCount; i++) {
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.SlidingMenu_menuWidth:
                    mMenuWidth = array.getDimensionPixelSize(attr, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,
                            context.getResources().getDisplayMetrics())); //第二个参数为默认边距
                    break;
            }
        }
        array.recycle(); //记得释放
        //获取屏幕宽度

        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);

        mScreenWidth = outMetrics.widthPixels;
    }


    /**
     * 设置子View高度和自己的宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //防止多次调用
        if (!once){
            mWapper = (LinearLayout) getChildAt(0); //外层布局
            mContent = (ViewGroup) mWapper.getChildAt(0);//内容布局
            mMenu =  mWapper.getChildAt(1);//侧滑出的菜单


            mContent.getLayoutParams().width = mScreenWidth;
            once = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 通过设置偏移量隐藏menu
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed){
            this.scrollTo(-mMenuWidth,0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        //System.out.println("laile");
         int scrollX = getScrollX();//向正方向滑动的距离
        switch (action){
            case MotionEvent.ACTION_UP:

                if (scrollX >= (mMenuWidth / 2)){
                    this.smoothScrollTo(mMenuWidth,0);
                    isOpen = true;
                }else {
                    this.smoothScrollTo(0,0);
                    isOpen = false;
                }
                if(mListener!=null){
                    mListener.onOpenOrClose(this,isOpen);
                }
               break;
        }

        return super.onTouchEvent(ev);
    }

    /**
     * @param isOpenOrClose
     */
    public void setOpen(boolean isOpenOrClose){
        if (isOpenOrClose){
            openMenu();
        }else {
            closeMenu();
        }
    }

    /**
     * 打开菜单按钮
     */
    public void openMenu(){
       /* if (isOpen){
            return;
        }*/
        this.smoothScrollTo(mMenuWidth,0);
        if(mListener!=null){
            mListener.onOpenOrClose(this,true);
        }
       // isOpen = true;
    }

    /**
     * 关闭菜单按钮
     */
    public void closeMenu(){
       /* if (!isOpen){
            return;
        }*/
        this.smoothScrollTo(0,0);
        if(mListener!=null){
            mListener.onOpenOrClose(this,false);
        }
        //isOpen = false;
    }


    /**
     * 切换菜单
     */
    public void toggle()
    {
        if (isOpen)
        {
            closeMenu();
        } else
        {
            openMenu();
        }
    }


    public void setOnOpenChangeListener(onOpenChangeListener listener){
        mListener = listener;
    }
    public interface onOpenChangeListener {
        void onOpenOrClose(SlidingMenu slidingMenu, boolean isOpen);
    }
}
