private static long latmGetValue(ParsableBitArray data){
  int bytesForValue=data.readBits(2);
  return data.readBits((bytesForValue + 1) * 8);
}
