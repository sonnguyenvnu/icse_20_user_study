package com.xuexiang.xui.widget.edittext.verify;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import androidx.appcompat.widget.AppCompatEditText;

/**
 *
 *
 * @author XUE
 * @since 2019/5/7 11:12
 */
public class PwdEditText extends AppCompatEditText {
    private TInputConnection mInputConnection;

    public PwdEditText(Context context) {
        super(context);
        init();
    }

    public PwdEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PwdEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mInputConnection = new TInputConnection(null,true);
    }

    /**
     * 当输入法和EditText建立连接的时候会通过这个方法返回一个InputConnection。
     * 我们需�?代�?�这个方法的父类方法生�?的InputConnection并返回我们自己的代�?�类。
     * */
    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        mInputConnection.setTarget(super.onCreateInputConnection(outAttrs));
        return mInputConnection;
    }

    public void setBackSpaceListener(TInputConnection.BackspaceListener backSpaceListener){
        mInputConnection.setBackspaceListener(backSpaceListener);
    }
}
