public int deprecated(){
  return buffer.getInt(offset + 24,java.nio.ByteOrder.LITTLE_ENDIAN);
}
