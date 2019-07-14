/** 
 * Calculates the scroll speed. <p>By default, LinearSmoothScroller assumes this method always returns the same value and caches the result of calling it.
 * @param displayMetrics DisplayMetrics to be used for real dimension calculations
 * @return The time (in ms) it should take for each pixel. For instance, if returned value is2 ms, it means scrolling 1000 pixels with LinearInterpolation should take 2 seconds.
 */
protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics){
  return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
}
