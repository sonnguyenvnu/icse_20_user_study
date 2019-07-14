/** 
 * Inserts one element into another array.
 */
@SuppressWarnings({"unchecked"}) public static <T>T[] insert(T[] dest,T src,int offset,Class componentType){
  T[] temp=(T[])Array.newInstance(componentType,dest.length + 1);
  System.arraycopy(dest,0,temp,0,offset);
  temp[offset]=src;
  System.arraycopy(dest,offset,temp,offset + 1,dest.length - offset);
  return temp;
}
