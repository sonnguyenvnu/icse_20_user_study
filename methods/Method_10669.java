/** 
 * ???????(api??19????) <p>??Activity?onCreat()???</p> <p>??????????????????????????</p> <p>android:clipToPadding="true"</p> <p>android:fitsSystemWindows="true"</p>
 * @param activity activity
 */
public static void setTransparentStatusBar(Activity activity){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
  }
}
