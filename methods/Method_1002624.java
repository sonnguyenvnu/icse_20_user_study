public VarDataEncodingEncoder length(final int value){
  buffer.putShort(offset + 0,(short)value,java.nio.ByteOrder.LITTLE_ENDIAN);
  return this;
}
