/** 
 * Get the current amplification values for all frequency bands.
 * @return current amplification values
 */
public final float[] amps(){
  float[] result=new float[bandCount];
  copy(bandAmps,result);
  return result;
}
