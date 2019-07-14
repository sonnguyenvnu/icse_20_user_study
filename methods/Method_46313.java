@Override public byte[] array(){
  if (byteBuf.hasArray()) {
    return byteBuf.array();
  }
 else {
    byte[] bs=new byte[byteBuf.readableBytes()];
    byteBuf.readBytes(bs);
    return bs;
  }
}
