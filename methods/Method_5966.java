private int readExpGolombCodeNum(){
  int leadingZeros=0;
  while (!readBit()) {
    leadingZeros++;
  }
  return (1 << leadingZeros) - 1 + (leadingZeros > 0 ? readBits(leadingZeros) : 0);
}
