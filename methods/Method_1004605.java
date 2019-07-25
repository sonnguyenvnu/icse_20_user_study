/** 
 * Returns a value that lies within <code>minValue</code> and <code>maxValue</code> by subtracting/adding <code>interval</code>.
 * @param n the input number
 * @param minValue the minimum value
 * @param maxValue the maximum value
 * @param interval the interval length
 * @return a value that lies within <code>minValue</code> and <code>maxValue</code> bysubtracting/adding <code>interval</code>
 */
private static double wrap(double n,final double minValue,final double maxValue,final double interval){
  if (minValue > maxValue) {
    throw new IllegalArgumentException("minValue must be smaller than maxValue: " + minValue + ">" + maxValue);
  }
  if (interval > maxValue - minValue + 1) {
    throw new IllegalArgumentException("interval must be equal or smaller than maxValue-minValue: " + "min: " + minValue + " max:" + maxValue + " int:" + interval);
  }
  while (n < minValue) {
    n+=interval;
  }
  while (n > maxValue) {
    n-=interval;
  }
  return n;
}
