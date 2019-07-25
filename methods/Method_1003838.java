@Override public ByteBuf sign(ByteBuf message,ByteBufAllocator byteBufAllocator){
  return Exceptions.uncheck(() -> {
    Mac mac=Mac.getInstance(secretKeySpec.getAlgorithm());
    mac.init(secretKeySpec);
    mac.update(message.nioBuffer());
    return Unpooled.wrappedBuffer(mac.doFinal());
  }
);
}
