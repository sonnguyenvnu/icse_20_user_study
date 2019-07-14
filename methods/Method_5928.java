/** 
 * Reads up to 32 bits.
 * @param numBits The number of bits to read.
 * @return An integer whose bottom n bits hold the read data.
 */
public int readBits(int numBits){
  if (numBits == 0) {
    return 0;
  }
  int returnValue=0;
  bitOffset+=numBits;
  while (bitOffset > 8) {
    bitOffset-=8;
    returnValue|=(data[byteOffset++] & 0xFF) << bitOffset;
  }
  returnValue|=(data[byteOffset] & 0xFF) >> (8 - bitOffset);
  returnValue&=0xFFFFFFFF >>> (32 - numBits);
  if (bitOffset == 8) {
    bitOffset=0;
    byteOffset++;
  }
  assertValidOffset();
  return returnValue;
}
