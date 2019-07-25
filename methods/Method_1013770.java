@Override public RedisClientProtocol<String> read(ByteBuf byteBuf){
  String data=readTilCRLFAsString(byteBuf);
  if (data == null) {
    return null;
  }
  int beginIndex=0;
  int endIndex=data.length();
  int dataLength=data.length();
  if (data.charAt(0) == PLUS_BYTE) {
    beginIndex=1;
  }
  if (data.charAt(dataLength - 2) == '\r' && data.charAt(dataLength - 1) == '\n') {
    endIndex-=2;
  }
  return new SimpleStringParser(data.substring(beginIndex,endIndex));
}
