protected static void updateIntBuffer(IntBuffer buf,int[] arr,int offset,int size){
  if (USE_DIRECT_BUFFERS || (buf.hasArray() && buf.array() != arr)) {
    buf.position(offset);
    buf.put(arr,offset,size);
    buf.rewind();
  }
}
