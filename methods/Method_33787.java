/** 
 * ? fragment ??? ImageView ????????
 * @param activity       fragment ??? activity
 * @param needOffsetView ??????? View
 */
public static void setTransparentForImageViewInFragment(Activity activity,View needOffsetView){
  setTranslucentForImageViewInFragment(activity,0,needOffsetView);
}
