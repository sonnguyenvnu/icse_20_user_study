@Override public RedisClientProtocol<Long> read(ByteBuf byteBuf){
  String data=readTilCRLFAsString(byteBuf);
  if (data == null) {
    return null;
  }
  if (data.charAt(0) != COLON_BYTE) {
    logger.debug("[read] first char expected is Colon (:)");
    return new LongParser(Long.valueOf(data.trim()));
  }
 else {
    return new LongParser(Long.valueOf(data.substring(1).trim()));
  }
}
