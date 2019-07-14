/** 
 * This is a screen density aware alternative to  {@link #setMinScale(float)}; it allows you to express the minimum allowed scale in terms of the maximum pixel density.
 * @param dpi Source image pixel density at minimum zoom.
 */
public final void setMaximumDpi(int dpi){
  DisplayMetrics metrics=getResources().getDisplayMetrics();
  float averageDpi=(metrics.xdpi + metrics.ydpi) / 2;
  setMinScale(averageDpi / dpi);
}
