@Override public V8ArrayBuffer twin(){
  v8.checkThread();
  checkReleased();
  return (V8ArrayBuffer)super.twin();
}
