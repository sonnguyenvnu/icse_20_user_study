/** 
 * Returns the number of elements residing in the buffer.
 * @return the number of elements in this buffer
 */
default int size(){
  return writes() - reads();
}
