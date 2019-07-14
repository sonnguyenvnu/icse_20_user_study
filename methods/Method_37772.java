/** 
 * Inserts one element into another <code>float</code> array.
 */
public static float[] insert(float[] dest,float src,int offset){
  float[] temp=new float[dest.length + 1];
  System.arraycopy(dest,0,temp,0,offset);
  temp[offset]=src;
  System.arraycopy(dest,offset,temp,offset + 1,dest.length - offset);
  return temp;
}
