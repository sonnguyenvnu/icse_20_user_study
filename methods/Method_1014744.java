public static RemotingCommand decode(final byte[] array){
  ByteBuffer byteBuffer=ByteBuffer.wrap(array);
  return decode(byteBuffer);
}
