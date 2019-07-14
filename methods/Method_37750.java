/** 
 * Resizes a <code>long</code> array.
 */
public static long[] resize(long[] buffer,int newSize){
  long[] temp=new long[newSize];
  System.arraycopy(buffer,0,temp,0,buffer.length >= newSize ? newSize : buffer.length);
  return temp;
}
