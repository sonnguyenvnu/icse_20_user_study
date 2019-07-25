public final byte[] array(){
  v8.checkThread();
  checkReleased();
  return byteBuffer.array();
}
