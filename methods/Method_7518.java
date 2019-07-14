public void writeBytes(NativeByteBuffer b){
  if (justCalc) {
    len+=b.limit();
  }
 else {
    b.rewind();
    buffer.put(b.buffer);
  }
}
