/** 
 * Shrinks the size of the list.
 * @param newSize {@code >= 0;} the new size
 */
public void shrink(int newSize){
  if (newSize < 0) {
    throw new IllegalArgumentException("newSize < 0");
  }
  if (newSize > size) {
    throw new IllegalArgumentException("newSize > size");
  }
  throwIfImmutable();
  size=newSize;
}
