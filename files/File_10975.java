package com.vondear.rxui.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 *
 * @author vondear
 * @date 2016/12/26
 */

public class RxFragmentTool {

    //----------------------------------------------------------------------------------------------Fragment的�?��?使用 start
    //在布局文件中直接使用标签
    /*    <fragment
            android:layout_below="@id/id_fragment_title"
            android:id="@+id/id_fragment_content"
            android:layout_width="fill_parent"
            android:layout_height="fill_parent" />*/

    //==============================================================================================Fragment的�?��?使用 end

    //----------------------------------------------------------------------------------------------Fragment的动�?使用 start

    /**
     * v4包下的使用
     * 动�?的使用Fragment
     *
     * 在布局文件中使用 FrameLayout 标签
     *
     * @param fragmentActivity
     * @param fragment
     * @param r_id_fragment    <FrameLayout android:id="@+id/r_id_fragment"/>
     */
    public static void showFragment(FragmentActivity fragmentActivity, Fragment fragment, int r_id_fragment) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(r_id_fragment, fragment);
        fragmentTransaction.commit();
    }

    /**
     * android.app.Activity下的使用
     * 动�?的使用Fragment
     *
     * 在布局文件中使用 FrameLayout 标签
     *
     * @param activity
     * @param fragment
     * @param r_id_fragment <FrameLayout android:id="@+id/r_id_fragment"/>
     */
    public static void showFragment(Activity activity, android.app.Fragment fragment, int r_id_fragment) {
        android.app.FragmentManager fragmentManager = activity.getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(r_id_fragment, fragment);
        fragmentTransaction.commit();
    }

    //==============================================================================================Fragment的动�?使用 start

    /**
     * v4包下的使用
     * 动�?的使用Fragment
     *
     * 在布局文件中使用 FrameLayout 标签
     *
     * @param fragmentActivity
     * @param fragmentLazy
     * @param r_id_fragment    <FrameLayout android:id="@+id/r_id_fragment"/>
     */
    public static void showFragmentLazy(FragmentActivity fragmentActivity, FragmentLazy fragmentLazy, int r_id_fragment) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(r_id_fragment, fragmentLazy);
        fragmentTransaction.commit();
        fragmentLazy.onHiddenChanged(true);
        fragmentLazy.onHiddenChanged(false);
    }

}
