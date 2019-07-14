/** 
 * Calculate desired size for the given View based on device orientation
 * @param context      The Context
 * @param parentWidth  The width of the Parent View
 * @param parentHeight The height of the Parent View
 * @return The desired size for the View
 */
public static int calcDesiredSize(Context context,int parentWidth,int parentHeight){
  int orientation=context.getResources().getConfiguration().orientation;
  int desiredSize=(orientation == Configuration.ORIENTATION_LANDSCAPE) ? parentWidth : parentHeight;
  return Math.min(desiredSize,parentWidth);
}
