/** 
 * ?? ????????????setContentView?????????
 * @param activity
 */
public static void FLAG_FULLSCREEN(Activity activity){
  activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
}
