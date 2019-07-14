/** 
 * Resizes a <code>float</code> array.
 */
public static float[] resize(float[] buffer,int newSize){
  float[] temp=new float[newSize];
  System.arraycopy(buffer,0,temp,0,buffer.length >= newSize ? newSize : buffer.length);
  return temp;
}
