/** 
 * Removes sub-array from <code>float</code> array.
 */
public static float[] remove(float[] buffer,int offset,int length){
  int len2=buffer.length - length;
  float[] temp=new float[len2];
  System.arraycopy(buffer,0,temp,0,offset);
  System.arraycopy(buffer,offset + length,temp,offset,len2 - offset);
  return temp;
}
