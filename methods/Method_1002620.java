public TokenCodecEncoder deprecated(final int value){
  buffer.putInt(offset + 24,value,java.nio.ByteOrder.LITTLE_ENDIAN);
  return this;
}
