@Override public ByteBuf content(){
  buf.touch();
  return buf;
}
