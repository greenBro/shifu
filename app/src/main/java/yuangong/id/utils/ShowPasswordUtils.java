package yuangong.id.utils;

import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.widget.CompoundButton;

import yuangong.id.view.ClearEditText;


/**
 * Created by Mathy on 2016/8/31 0031.
 * <p>
 * 显示隐藏密码工具
 */
public class ShowPasswordUtils implements CompoundButton.OnCheckedChangeListener {

    private ClearEditText mClearEditText;

    public ShowPasswordUtils(ClearEditText clearEditText) {
        mClearEditText = clearEditText;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mClearEditText.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        } else {
            mClearEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        //设置光标在末尾
        setCursor(mClearEditText.getText());
    }


    /**
     * 设置光标在末尾
     *
     * @param text
     */
    public void setCursor(Editable text) {
        Selection.setSelection(text, text.length());
    }


}
