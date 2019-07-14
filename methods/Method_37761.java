/** 
 * Returns subarray.
 */
public static float[] subarray(float[] buffer,int offset,int length){
  float[] temp=new float[length];
  System.arraycopy(buffer,offset,temp,0,length);
  return temp;
}
