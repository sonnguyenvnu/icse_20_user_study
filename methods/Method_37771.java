/** 
 * Inserts one element into another <code>long</code> array.
 */
public static long[] insert(long[] dest,long src,int offset){
  long[] temp=new long[dest.length + 1];
  System.arraycopy(dest,0,temp,0,offset);
  temp[offset]=src;
  System.arraycopy(dest,offset,temp,offset + 1,dest.length - offset);
  return temp;
}
