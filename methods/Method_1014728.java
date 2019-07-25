/** 
 * get.
 * @param index index.
 * @return element.
 */
public E get(int index){
  if (index >= mSize) {
    throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + mSize);
  }
  return index < 0 ? mElements.get(index + mSize) : mElements.get(index);
}
