protected static ByteBuffer __create(int elements,int elementSize){
  apiCheckAllocation(elements,getBytes(elements,elementSize),0x7FFF_FFFFL);
  return ByteBuffer.allocateDirect(elements * elementSize).order(ByteOrder.nativeOrder());
}
