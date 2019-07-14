/** 
 * Grows the buffer.
 */
private void grow(final int minCapacity){
  final int oldCapacity=buffer.length;
  int newCapacity=oldCapacity << 1;
  if (newCapacity - minCapacity < 0) {
    newCapacity=minCapacity + 512;
  }
  buffer=Arrays.copyOf(buffer,newCapacity);
}
