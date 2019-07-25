/** 
 * Sets the bit specified by the index to  {@code false}.
 * @param index the index of the bit to be cleared.
 * @throws IndexOutOfBoundsException if the specified index is negative.
 */
public void clear(int index){
  if (index < 0 || index >= size)   throw new IndexOutOfBoundsException("index: " + index);
  int wordIndex=wordIndex(index);
  words[wordIndex]&=~(1L << index);
}
