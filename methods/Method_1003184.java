/** 
 * Insert a value in an array. A new array is created if required.
 * @param old the old array
 * @param oldSize the old size
 * @param pos the position
 * @param x the value to insert
 * @return the (new) array
 */
@SuppressWarnings("unchecked") public static <T>T[] insert(T[] old,int oldSize,int pos,T x){
  T[] result;
  if (old.length > oldSize) {
    result=old;
  }
 else {
    result=(T[])Array.newInstance(old.getClass().getComponentType(),oldSize + 1 + COPY_THRESHOLD);
    if (pos > 0) {
      System.arraycopy(old,0,result,0,pos);
    }
  }
  if (oldSize - pos > 0) {
    System.arraycopy(old,pos,result,pos + 1,oldSize - pos);
  }
  result[pos]=x;
  return result;
}
