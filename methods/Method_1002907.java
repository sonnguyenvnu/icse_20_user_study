@Override public byte[] array(){
  if (buf.hasArray() && buf.arrayOffset() == 0 && buf.array().length == length) {
    return buf.array();
  }
 else {
    return ByteBufUtil.getBytes(buf);
  }
}
