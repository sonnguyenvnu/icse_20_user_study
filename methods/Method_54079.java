public static ByteBuffer nmPrivate(long struct,int capacity){
  return memByteBuffer(memGetAddress(struct + AIScene.MPRIVATE),capacity);
}
