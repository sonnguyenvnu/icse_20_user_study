/** 
 * Inserts one array into another array.
 */
@SuppressWarnings({"unchecked"}) public static <T>T[] insert(T[] dest,T[] src,int offset,Class componentType){
  T[] temp=(T[])Array.newInstance(componentType,dest.length + src.length);
  System.arraycopy(dest,0,temp,0,offset);
  System.arraycopy(src,0,temp,offset,src.length);
  System.arraycopy(dest,offset,temp,src.length + offset,dest.length - offset);
  return temp;
}
