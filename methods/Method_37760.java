/** 
 * Returns subarray.
 */
public static long[] subarray(long[] buffer,int offset,int length){
  long[] temp=new long[length];
  System.arraycopy(buffer,offset,temp,0,length);
  return temp;
}
