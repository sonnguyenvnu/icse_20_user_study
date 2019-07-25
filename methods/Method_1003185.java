/** 
 * Delete a value in an array. A new array is created if required.
 * @param old the old array
 * @param oldSize the old size
 * @param pos the position
 * @return the (new) array
 */
@SuppressWarnings("unchecked") public static <T>T[] remove(T[] old,int oldSize,int pos){
  T[] result;
  if (old.length - oldSize < COPY_THRESHOLD) {
    result=old;
  }
 else {
    result=(T[])Array.newInstance(old.getClass().getComponentType(),oldSize - 1);
    System.arraycopy(old,0,result,0,Math.min(oldSize - 1,pos));
  }
  if (pos < oldSize) {
    System.arraycopy(old,pos + 1,result,pos,oldSize - pos - 1);
  }
  return result;
}
