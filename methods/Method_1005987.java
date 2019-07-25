public void shrink(int size){
  if (mBuffer.capacity() <= size) {
    return;
  }
  int endIndex=mLength;
  int beginIndex=mLength - size;
  byte[] bytes=toBytes(beginIndex,endIndex);
  mBuffer=ByteBuffer.wrap(bytes);
  mBuffer.position(bytes.length);
  mLength=bytes.length;
}
