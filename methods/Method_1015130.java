public static ByteChannelDurableOutput create(int blockSize){
  try {
    return new ByteChannelDurableOutput(new ByteBufferWritableChannel(blockSize),blockSize);
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
}
