private byte[] longToBytes(long value){
  buffer[0]=(byte)(value >>> 56);
  buffer[1]=(byte)(value >>> 48);
  buffer[2]=(byte)(value >>> 40);
  buffer[3]=(byte)(value >>> 32);
  buffer[4]=(byte)(value >>> 24);
  buffer[5]=(byte)(value >>> 16);
  buffer[6]=(byte)(value >>> 8);
  buffer[7]=(byte)(value);
  return buffer;
}
