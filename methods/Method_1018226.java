/** 
 * remove the element at the specified position use System.arraycopy instead of a loop may be more efficient
 * @param idx
 */
public void remove(int idx){
  if (idx < 0 || idx > size)   throw new IndexOutOfBoundsException();
  int numMove=size - idx - 1;
  if (numMove > 0)   System.arraycopy(items,idx + 1,items,idx,numMove);
  size--;
}
