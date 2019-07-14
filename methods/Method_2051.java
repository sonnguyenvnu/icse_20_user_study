/** 
 * Invoke one into the Activity to get info about the Display size
 * @param activity The Activity
 */
public static void initSizeData(Activity activity){
  DisplayMetrics metrics=new DisplayMetrics();
  activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
  DISPLAY_WIDTH=metrics.widthPixels;
  DISPLAY_HEIGHT=metrics.heightPixels;
}
