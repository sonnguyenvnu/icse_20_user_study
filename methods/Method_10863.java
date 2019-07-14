/** 
 * ???????????????
 * @param distance  ????,????
 * @param currScale ??????
 * @param context
 * @return
 */
public static double metreToScreenPixel(double distance,double currScale,Context context){
  float dpi=context.getResources().getDisplayMetrics().densityDpi;
  double resolution=(25.39999918 / dpi) * currScale / 1000;
  return distance / resolution;
}
