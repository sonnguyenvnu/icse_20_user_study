public static void nmPrivate(long struct,ByteBuffer value){
  memPutAddress(struct + AIScene.MPRIVATE,memAddress(value));
}
