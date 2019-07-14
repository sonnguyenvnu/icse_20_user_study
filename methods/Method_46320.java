public static String toString(ByteBuf byteBuf){
  if (byteBuf == null) {
    return null;
  }
  byte[] bs;
  int readIndex=byteBuf.readerIndex();
  if (byteBuf.hasArray()) {
    bs=byteBuf.array();
  }
 else {
    bs=new byte[byteBuf.readableBytes()];
    byteBuf.readBytes(bs);
  }
  byteBuf.readerIndex(readIndex);
  return new String(bs,RpcConstants.DEFAULT_CHARSET);
}
