public int version(){
  return (buffer.getShort(offset + 6,java.nio.ByteOrder.LITTLE_ENDIAN) & 0xFFFF);
}
