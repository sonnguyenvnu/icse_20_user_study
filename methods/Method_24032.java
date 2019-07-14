protected static void updateShortBuffer(ShortBuffer buf,short[] arr,int offset,int size){
  if (USE_DIRECT_BUFFERS || (buf.hasArray() && buf.array() != arr)) {
    buf.position(offset);
    buf.put(arr,offset,size);
    buf.rewind();
  }
}
