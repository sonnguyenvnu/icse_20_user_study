@Override public ByteBufHttpData duplicate(){
  return new ByteBufHttpData(buf.duplicate(),endOfStream);
}
