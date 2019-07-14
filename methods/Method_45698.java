public ByteBuffer toByteBuffer(){
  return ByteBuffer.wrap(mBuffer,0,mCount);
}
