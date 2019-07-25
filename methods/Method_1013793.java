@Override public ByteBuf output(){
  return new SimpleStringParser(String.format("%s %s",ProxyProtocol.KEY_WORD,getContent())).format();
}
