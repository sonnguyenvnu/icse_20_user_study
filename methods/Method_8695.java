private int getBits(int bitOffset){
  return (data[bitOffset / 8] >> (bitOffset % 8)) & 0x3;
}
