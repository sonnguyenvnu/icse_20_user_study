/** 
 * {@inheritDoc}
 */
@Override public void insert(int index,KType e1){
  assert (index >= 0 && index <= size()) : "Index " + index + " out of bounds [" + 0 + ", " + size() + "].";
  ensureBufferSpace(1);
  System.arraycopy(buffer,index,buffer,index + 1,elementsCount - index);
  buffer[index]=e1;
  elementsCount++;
}
