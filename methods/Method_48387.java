public static ByteBuffer oneByteBuffer(int len){
  ByteBuffer res=ByteBuffer.allocate(len);
  for (int i=0; i < len; i++)   res.put((byte)-1);
  res.flip();
  return res;
}
