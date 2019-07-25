@Override public ByteBuf format(){
  ByteBuf byteBuf=getWriteByteBuf();
  if (logWrite && logger.isDebugEnabled()) {
    logger.info("[getWriteBytes]" + getPayloadAsString());
  }
  return byteBuf;
}
