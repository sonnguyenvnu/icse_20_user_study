/** 
 * Delete a value in an array. A new array is created if required.
 * @param old the old array
 * @param oldSize the old size
 * @param pos the position
 * @return the (new) array
 */
protected static int[] remove(int[] old,int oldSize,int pos){
  int[] result;
  if (old.length - oldSize < COPY_THRESHOLD) {
    result=old;
  }
 else {
    result=new int[oldSize - 1];
    System.arraycopy(old,0,result,0,Math.min(oldSize - 1,pos));
  }
  if (pos < oldSize) {
    System.arraycopy(old,pos + 1,result,pos,oldSize - pos - 1);
  }
  return result;
}
