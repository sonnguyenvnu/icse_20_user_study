/** 
 * Returns a new array containing all of a, with additional extra space added (zero initialized).
 * @param a
 * @param additional
 * @return
 */
public static double[] extend(double[] a,int additional){
  double[] ret=new double[a.length + additional];
  System.arraycopy(a,0,ret,0,a.length);
  return ret;
}
