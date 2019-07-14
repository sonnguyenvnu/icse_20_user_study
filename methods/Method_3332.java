/** 
 * ??
 * @param cdf ??????
 * @return
 */
protected static int drawFrom(double[] cdf){
  return -Arrays.binarySearch(cdf,Math.random()) - 1;
}
