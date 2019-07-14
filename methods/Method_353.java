private int readUnsignedShort(final int index){
  byte[] b=this.b;
  return ((b[index] & 0xFF) << 8) | (b[index + 1] & 0xFF);
}
