public final V8ArrayBuffer flip(){
  v8.checkThread();
  checkReleased();
  byteBuffer.flip();
  return this;
}
