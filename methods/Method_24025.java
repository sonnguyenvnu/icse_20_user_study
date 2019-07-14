protected static void updateByteBuffer(ByteBuffer buf,byte[] arr,int offset,int size){
  if (USE_DIRECT_BUFFERS || (buf.hasArray() && buf.array() != arr)) {
    buf.position(offset);
    buf.put(arr,offset,size);
    buf.rewind();
  }
}
