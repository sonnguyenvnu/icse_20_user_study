private boolean shouldSkipByte(int offset){
  return 2 <= offset && offset < byteLimit && data[offset] == (byte)0x03 && data[offset - 2] == (byte)0x00 && data[offset - 1] == (byte)0x00;
}
