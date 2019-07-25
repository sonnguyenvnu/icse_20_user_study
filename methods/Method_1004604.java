/** 
 * @since 6.0.0
 */
public static double Clip(final double n,final double minValue,final double maxValue){
  return Math.min(Math.max(n,minValue),maxValue);
}
