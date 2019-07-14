private static int readLittleEndian16(byte[] input,int offset){
  int value=input[offset] & 0xFF;
  value|=(input[offset + 1] & 0xFF) << 8;
  return value;
}
