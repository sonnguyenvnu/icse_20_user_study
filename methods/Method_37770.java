/** 
 * Inserts one element into another <code>int</code> array.
 */
public static int[] insert(int[] dest,int src,int offset){
  int[] temp=new int[dest.length + 1];
  System.arraycopy(dest,0,temp,0,offset);
  temp[offset]=src;
  System.arraycopy(dest,offset,temp,offset + 1,dest.length - offset);
  return temp;
}
