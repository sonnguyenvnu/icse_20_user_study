public final V8ArrayBuffer rewind(){
  v8.checkThread();
  checkReleased();
  byteBuffer.rewind();
  return this;
}
