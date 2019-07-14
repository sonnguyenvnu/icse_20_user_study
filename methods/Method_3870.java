/** 
 * Calculates the time it should take to scroll the given distance (in pixels)
 * @param dx Distance in pixels that we want to scroll
 * @return Time in milliseconds
 * @see #calculateSpeedPerPixel(android.util.DisplayMetrics)
 */
protected int calculateTimeForScrolling(int dx){
  return (int)Math.ceil(Math.abs(dx) * getSpeedPerPixel());
}
