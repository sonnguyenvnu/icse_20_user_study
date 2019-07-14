package com.example.jingbin.cloudreader.utils;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jingbin.cloudreader.R;
import com.example.jingbin.cloudreader.data.UserUtil;
import com.example.jingbin.cloudreader.data.model.LoginModel;
import com.example.jingbin.cloudreader.data.room.Injection;
import com.example.jingbin.cloudreader.data.room.User;
import com.example.jingbin.cloudreader.data.room.UserDataCallback;
import com.example.jingbin.cloudreader.view.OnLoginListener;


/**
 * @author jingbin
 * @data 2018/2/27
 * @Description
 */

public class DialogBuild {

    /**
     * 显示自定义布局
     */
    public static void showCustom(View v, String content, String buttonText, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        View view = View.inflate(v.getContext(), R.layout.title_douban_top, null);
        TextView titleTop = view.findViewById(R.id.title_top);
        titleTop.setText(content);
        builder.setView(view);
        builder.setPositiveButton(buttonText, clickListener);
        builder.show();
    }

    public static void showCustom(View v, String content, String positiveText, String negativeText, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        View view = View.inflate(v.getContext(), R.layout.title_douban_top, null);
        TextView titleTop = view.findViewById(R.id.title_top);
        titleTop.setText(content);
        builder.setView(view);
        builder.setPositiveButton(positiveText, clickListener);
        builder.setNegativeButton(negativeText, null);
        builder.show();
    }

    public static void show(View v, String message, String buttonText, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("�??示");
        builder.setMessage(message);
        builder.setPositiveButton(buttonText, clickListener);
        builder.show();
    }

    /**
     * 显示选项的AlertDialog
     */
    public static void showItems(View v, String content) {
        String[] items = {"�?制", "分享"};
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setItems(items, (dialog, which) -> {
            switch (which) {
                case 0:
                    BaseTools.copy(content);
                    ToastUtil.showToast("�?制�?功");
                    break;
                case 1:
                    ShareUtils.share(v.getContext(), content);
                    break;
                default:
                    break;
            }
        });
        builder.show();
    }

    /**
     * 用于账�?�登录
     */
    public static void showItems(View v, OnLoginListener listener) {
        Injection.get().getSingleBean(new UserDataCallback() {
            @Override
            public void onDataNotAvailable() {
                String[] items = {"GitHub账�?�", "玩安�?�账�?�"};
                showDialog(v, items, listener, false);
            }

            @Override
            public void getData(User bean) {
                String[] items = {"GitHub账�?�", "退出玩安�?�（" + bean.getUsername() + "）"};
                showDialog(v, items, listener, true);
            }
        });

    }

    private static void showDialog(View v, String[] items, OnLoginListener listener, boolean isLogin) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("账�?�登录");
        builder.setItems(items, (dialog, which) -> {
            switch (which) {
                case 0:
                    listener.loginGitHub();
                    break;
                case 1:
                    if (isLogin) {
                        new LoginModel().logout(() -> {
                            Injection.get().deleteAllData();
                            UserUtil.handleLoginFailure();
                            ToastUtil.showToastLong("退出�?功");
                        });
                    } else {
                        listener.loginWanAndroid();
                    }
                    break;
                default:
                    break;
            }
        });
        builder.show();
    }

    /**
     * 编辑收�?网�?�
     */
    public static void show(View v, String name, String link, OnEditClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("编辑");
        View inflate = View.inflate(v.getContext(), R.layout.dialog_eidt_url, null);
        builder.setView(inflate);
        AppCompatEditText etName = inflate.findViewById(R.id.et_name);
        AppCompatEditText etLink = inflate.findViewById(R.id.et_link);
        if (!TextUtils.isEmpty(name)) {
            etName.setText(name);
            etName.setSelection(name.length());
        }
        etLink.setText(link);
        builder.setNegativeButton("�?�消", null);
        builder.setPositiveButton("编辑完�?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = etName.getText().toString().trim();
                String link = etLink.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    ToastUtil.showToastLong("请输入�??称");
                    return;
                }
                if (TextUtils.isEmpty(link)) {
                    ToastUtil.showToastLong("请输入链接");
                    return;
                }
                listener.onClick(name, link);
            }
        });
        builder.show();
    }

    public interface OnEditClickListener {
        void onClick(String name, String link);
    }

}
