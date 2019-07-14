/** 
 * Removes sub-array from <code>long</code> array.
 */
public static long[] remove(long[] buffer,int offset,int length){
  int len2=buffer.length - length;
  long[] temp=new long[len2];
  System.arraycopy(buffer,0,temp,0,offset);
  System.arraycopy(buffer,offset + length,temp,offset,len2 - offset);
  return temp;
}
