private byte[] intToBytes(int value){
  buffer[0]=(byte)(value >>> 24);
  buffer[1]=(byte)(value >>> 16);
  buffer[2]=(byte)(value >>> 8);
  buffer[3]=(byte)(value);
  return buffer;
}
