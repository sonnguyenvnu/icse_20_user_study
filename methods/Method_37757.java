/** 
 * Returns subarray.
 */
@SuppressWarnings({"unchecked"}) public static <T>T[] subarray(T[] buffer,int offset,int length,Class<T> componentType){
  T[] temp=(T[])Array.newInstance(componentType,length);
  System.arraycopy(buffer,offset,temp,0,length);
  return temp;
}
