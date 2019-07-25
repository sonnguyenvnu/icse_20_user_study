public MessageHeaderEncoder version(final int value){
  buffer.putShort(offset + 6,(short)value,java.nio.ByteOrder.LITTLE_ENDIAN);
  return this;
}
