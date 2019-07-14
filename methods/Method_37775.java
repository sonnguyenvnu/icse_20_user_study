/** 
 * Inserts one array into another at given offset.
 */
@SuppressWarnings({"unchecked"}) public static <T>T[] insertAt(T[] dest,T[] src,int offset,Class componentType){
  T[] temp=(T[])Array.newInstance(componentType,dest.length + src.length - 1);
  System.arraycopy(dest,0,temp,0,offset);
  System.arraycopy(src,0,temp,offset,src.length);
  System.arraycopy(dest,offset + 1,temp,src.length + offset,dest.length - offset - 1);
  return temp;
}
