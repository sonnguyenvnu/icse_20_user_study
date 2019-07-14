/** 
 * ? fragment ??? ImageView ????????
 * @param activity       fragment ??? activity
 * @param statusBarAlpha ??????
 * @param needOffsetView ??????? View
 */
public static void setTranslucentForImageViewInFragment(Activity activity,int statusBarAlpha,View needOffsetView){
  setTranslucentForImageView(activity,statusBarAlpha,needOffsetView);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
    clearPreviousSetting(activity);
  }
}
