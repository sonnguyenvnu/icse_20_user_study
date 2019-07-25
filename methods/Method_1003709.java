private static byte peek(ByteBuf buffer){
  return buffer.getByte(buffer.readerIndex());
}
