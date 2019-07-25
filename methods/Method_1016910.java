/** 
 * Return the standard deviation using 1/n, not the unbiased 1/(n-1). 
 */
public static double stddev(double[] m){
  double mean=mean(m);
  double s=0;
  for (int i=0; i < m.length; i++)   s+=(m[i] - mean) * (m[i] - mean);
  return Math.sqrt(s / m.length);
}
