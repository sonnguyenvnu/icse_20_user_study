/** 
 * Inserts one element into another <code>boolean</code> array.
 */
public static boolean[] insert(boolean[] dest,boolean src,int offset){
  boolean[] temp=new boolean[dest.length + 1];
  System.arraycopy(dest,0,temp,0,offset);
  temp[offset]=src;
  System.arraycopy(dest,offset,temp,offset + 1,dest.length - offset);
  return temp;
}
