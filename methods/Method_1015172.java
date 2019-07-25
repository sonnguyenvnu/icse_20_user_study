private void resize(int newCapacity){
  Object[] nElements=new Object[newCapacity];
  int truncatedSize=Math.min(size,elements.length - offset);
  System.arraycopy(elements,offset,nElements,0,truncatedSize);
  if (size != truncatedSize) {
    System.arraycopy(elements,0,nElements,truncatedSize,size - truncatedSize);
  }
  mask=nElements.length - 1;
  elements=nElements;
  offset=0;
}
