/** 
 * Inserts one array into another <code>int</code> array.
 */
public static int[] insert(int[] dest,int[] src,int offset){
  int[] temp=new int[dest.length + src.length];
  System.arraycopy(dest,0,temp,0,offset);
  System.arraycopy(src,0,temp,offset,src.length);
  System.arraycopy(dest,offset,temp,src.length + offset,dest.length - offset);
  return temp;
}
