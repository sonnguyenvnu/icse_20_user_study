public static ByteBuffer zeroByteBuffer(int len){
  ByteBuffer res=ByteBuffer.allocate(len);
  for (int i=0; i < len; i++)   res.put((byte)0);
  res.flip();
  return res;
}
