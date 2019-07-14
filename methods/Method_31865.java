/** 
 * Gets the value at the specified index. <p> The only index supported by this period is zero.
 * @param index  the index to retrieve, which must be zero
 * @return the value of the field at the specified index
 * @throws IndexOutOfBoundsException if the index is invalid
 */
public int getValue(int index){
  if (index != 0) {
    throw new IndexOutOfBoundsException(String.valueOf(index));
  }
  return getValue();
}
