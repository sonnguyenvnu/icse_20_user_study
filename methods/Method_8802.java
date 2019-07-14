public void resizeBuffer(){
  if (buffer != null) {
    buffer=null;
  }
  allocatedCount=Math.max(allocatedCount * 2,DEFAULT_STATE_SIZE);
  buffer=ByteBuffer.allocateDirect(allocatedCount * 5 * 4);
  buffer.order(ByteOrder.nativeOrder());
  buffer.position(0);
}
