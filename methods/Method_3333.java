/** 
 * ????????????
 * @param freq
 */
protected void normalize(float[] freq){
  float sum=MathUtility.sum(freq);
  for (int i=0; i < freq.length; i++)   freq[i]/=sum;
}
