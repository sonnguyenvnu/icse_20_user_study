package com.vondear.rxtool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.vondear.rxtool.interfaces.OnDoListener;
import com.vondear.rxtool.interfaces.OnSimpleListener;
import com.vondear.rxtool.view.RxToast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author vondear
 * @date 2016/1/24
 * RxTools的常用工具类
 * <p>
 * For the brave souls who get this far: You are the chosen ones,
 * the valiant knights of programming who toil away, without rest,
 * fixing our most awful code. To you, true saviors, kings of men,
 * I say this: never gonna give you up, never gonna let you down,
 * never gonna run around and desert you. Never gonna make you cry,
 * never gonna say goodbye. Never gonna tell a lie and hurt you.
 * <p>
 * 致终于�?�到这里的勇敢的人：
 * 你是被上�?选中的人，是英勇的�?�?敌辛苦的�?�?眠�?休的�?�修改我们这最棘手的代�?的编程骑士。
 * 你，我们的救世主，人中之龙，我�?对你说：永远�?�?放弃，永远�?�?对自己失望，永远�?�?逃走，辜负了自己，
 * 永远�?�?哭啼，永远�?�?说�?�?，永远�?�?说谎�?�伤害自己。
 */
public class RxTool {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static long lastClickTime;

    /**
     * �?始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        RxTool.context = context.getApplicationContext();
        RxCrashTool.init(context);
    }

    /**
     * 在�?�?获�?��?到 Context 的情况下，�?��?�以使用�?方法获�?� Context
     * <p>
     * 获�?�ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("请先调用init()方法");
    }
    //==============================================================================================延时任务�?装 end

    //----------------------------------------------------------------------------------------------延时任务�?装 start
    public static void delayToDo(long delayTime, final OnSimpleListener onSimpleListener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //execute the task
                onSimpleListener.doSomething();
            }
        }, delayTime);
    }

    /**
     * 倒计时
     *
     * @param textView 控件
     * @param waitTime 倒计时总时长
     * @param interval 倒计时的间隔时间
     * @param hint     倒计时完毕时显示的文字
     */
    public static void countDown(final TextView textView, long waitTime, long interval, final String hint) {
        textView.setEnabled(false);
        android.os.CountDownTimer timer = new android.os.CountDownTimer(waitTime, interval) {

            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(String.format("剩下 %d S", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                textView.setEnabled(true);
                textView.setText(hint);
            }
        };
        timer.start();
    }

    /**
     * 手动计算出listView的高度，但是�?�?具有滚动效果
     *
     * @param listView
     */
    public static void fixListViewHeight(ListView listView) {
        // 如果没有设置数�?�适�?器，则ListView没有�?项，返回。
        ListAdapter listAdapter = listView.getAdapter();
        int totalHeight = 0;
        if (listAdapter == null) {
            return;
        }
        for (int index = 0, len = listAdapter.getCount(); index < len; index++) {
            View listViewItem = listAdapter.getView(index, null, listView);
            // 计算�?项View 的宽高
            listViewItem.measure(0, 0);
            // 计算所有�?项的高度
            totalHeight += listViewItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // listView.getDividerHeight()获�?��?项间分隔符的高度
        // params.height设置ListView完全显示需�?的高度
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    //---------------------------------------------MD5加密-------------------------------------------

    /**
     * 生�?MD5加密32�?字符串
     *
     * @param MStr :需�?加密的字符串
     * @return
     */
    public static String Md5(String MStr) {
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(MStr.getBytes());
            return bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            return String.valueOf(MStr.hashCode());
        }
    }

    // MD5内部算法---------------�?能修改!
    private static String bytesToHexString(byte[] bytes) {
        // http://stackoverflow.com/questions/332079
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
    //============================================MD5加密============================================

    /**
     * 根�?�资�?�??称获�?�资�? id
     * <p>
     * �?�??倡使用这个方法获�?�资�?,比其直接获�?�ID效率慢
     * <p>
     * 例如
     * getResources().getIdentifier("ic_launcher", "drawable", getPackageName());
     *
     * @param context
     * @param name
     * @param defType
     * @return
     */
    public final static int getResIdByName(Context context, String name, String defType) {
        return context.getResources().getIdentifier(name, defType, context.getPackageName());
    }

    public static boolean isFastClick(int millisecond) {
        long curClickTime = System.currentTimeMillis();
        long interval = (curClickTime - lastClickTime);

        if (0 < interval && interval < millisecond) {
            // 超过点击间隔�?��?将lastClickTime�?置为当�?点击时间
            return true;
        }
        lastClickTime = curClickTime;
        return false;
    }

    /**
     * Edittext 首�?�?数点自动加零，最多两�?�?数
     *
     * @param editText
     */
    public static void setEdTwoDecimal(EditText editText) {
        setEdDecimal(editText, 2);
    }

    /**
     * �?��?许数字和汉字
     * @param editText
     */
    public static void setEdType(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void
            beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void
            onTextChanged(CharSequence s, int start, int before, int count) {
                String editable = editText.getText().toString();
                String str = stringFilter(editable);
                if (!editable.equals(str)) {
                    editText.setText(str);
                    //设置新的光标所在�?置
                    editText.setSelection(str.length());
                }
            }

            @Override
            public void
            afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * // �?��?许数字和汉字
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String stringFilter(String str) throws PatternSyntaxException {

        String regEx = "[^0-9\u4E00-\u9FA5]";//正则表达�?
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static void setEdDecimal(EditText editText, int count) {
        if (count < 0) {
            count = 0;
        }

        count += 1;

        editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);

        //设置字符过滤
        final int finalCount = count;
        editText.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (".".contentEquals(source) && dest.toString().length() == 0) {
                    return "0.";
                }
                if (dest.toString().contains(".")) {
                    int index = dest.toString().indexOf(".");
                    int mlength = dest.toString().substring(index).length();
                    if (mlength == finalCount) {
                        return "";
                    }
                }

                if (dest.toString().equals("0") && source.equals("0")) {
                    return "";
                }

                return null;
            }
        }});
    }

    /**
     * @param editText       输入框控件
     * @param number         �?数
     *                       1 -> 1
     *                       2 -> 01
     *                       3 -> 001
     *                       4 -> 0001
     * @param isStartForZero 是�?�从000开始
     *                       true -> 从 000 开始
     *                       false -> 从 001 开始
     */
    public static void setEditNumberAuto(final EditText editText, final int number, final boolean isStartForZero) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    setEditNumber(editText, number, isStartForZero);
                }
            }
        });
    }

    /**
     * @param editText       输入框控件
     * @param number         �?数
     *                       1 -> 1
     *                       2 -> 01
     *                       3 -> 001
     *                       4 -> 0001
     * @param isStartForZero 是�?�从000开始
     *                       true -> 从 000 开始
     *                       false -> 从 001 开始
     */
    public static void setEditNumber(EditText editText, int number, boolean isStartForZero) {
        StringBuilder s = new StringBuilder(editText.getText().toString());
        StringBuilder temp = new StringBuilder();

        int i;
        for (i = s.length(); i < number; ++i) {
            s.insert(0, "0");
        }
        if (!isStartForZero) {
            for (i = 0; i < number; ++i) {
                temp.append("0");
            }

            if (s.toString().equals(temp.toString())) {
                s = new StringBuilder(temp.substring(1) + "1");
            }
        }
        editText.setText(s.toString());
    }

    /**
     * 获�?�
     * @return
     */
    public static Handler getBackgroundHandler() {
        HandlerThread thread = new HandlerThread("background");
        thread.start();
        return new Handler(thread.getLooper());
    }

    public static void initFastClickAndVibrate(Context mContext, OnDoListener onRxSimple) {
        if (RxTool.isFastClick(RxConstants.FAST_CLICK_TIME)) {
            RxToast.normal("请�?�?�?�?点击");
            return;
        } else {
            RxVibrateTool.vibrateOnce(mContext, RxConstants.VIBRATE_TIME);
            onRxSimple.doSomething();
        }
    }
}
