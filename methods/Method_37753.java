/** 
 * Removes sub-array from <code>short</code> array.
 */
public static short[] remove(short[] buffer,int offset,int length){
  int len2=buffer.length - length;
  short[] temp=new short[len2];
  System.arraycopy(buffer,0,temp,0,offset);
  System.arraycopy(buffer,offset + length,temp,offset,len2 - offset);
  return temp;
}
