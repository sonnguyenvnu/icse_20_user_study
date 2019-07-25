@Override public ByteBuf format(){
  return new SimpleStringParser(String.format("%s %s",ProxyProtocol.KEY_WORD,optionParser.output())).format();
}
