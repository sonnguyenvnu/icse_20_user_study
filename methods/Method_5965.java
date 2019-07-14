/** 
 * Returns whether it is possible to read an Exp-Golomb-coded integer starting from the current offset. The offset is not modified.
 * @return Whether it is possible to read an Exp-Golomb-coded integer.
 */
public boolean canReadExpGolombCodedNum(){
  int initialByteOffset=byteOffset;
  int initialBitOffset=bitOffset;
  int leadingZeros=0;
  while (byteOffset < byteLimit && !readBit()) {
    leadingZeros++;
  }
  boolean hitLimit=byteOffset == byteLimit;
  byteOffset=initialByteOffset;
  bitOffset=initialBitOffset;
  return !hitLimit && canReadBits(leadingZeros * 2 + 1);
}
