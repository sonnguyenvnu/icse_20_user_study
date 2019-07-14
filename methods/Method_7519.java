public int getIntFromByte(byte b){
  return b >= 0 ? b : ((int)b) + 256;
}
