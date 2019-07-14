/** 
 * ??????????????????????????(?)
 * @param pxlength
 * @param currScale
 * @param context
 * @return
 */
public static double screenPixelToMetre(double pxlength,double currScale,Context context){
  float dpi=context.getResources().getDisplayMetrics().densityDpi;
  double resolution=(25.39999918 / dpi) * currScale / 1000;
  return pxlength * resolution;
}
