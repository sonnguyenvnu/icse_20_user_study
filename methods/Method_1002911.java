@Override public ByteBufHttpData replace(ByteBuf content){
  requireNonNull(content,"content");
  content.touch();
  return new ByteBufHttpData(content,endOfStream);
}
