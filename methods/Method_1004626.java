/** 
 * Wraps the given value into the inclusive-exclusive interval between min and max.
 * @param n   The value to wrap.
 * @param min The minimum.
 * @param max The maximum.
 */
static double wrap(double n,double min,double max){
  return (n >= min && n < max) ? n : (mod(n - min,max - min) + min);
}
