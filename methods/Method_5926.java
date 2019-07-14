/** 
 * Skips bits and moves current reading position forward.
 * @param numBits The number of bits to skip.
 */
public void skipBits(int numBits){
  int numBytes=numBits / 8;
  byteOffset+=numBytes;
  bitOffset+=numBits - (numBytes * 8);
  if (bitOffset > 7) {
    byteOffset++;
    bitOffset-=8;
  }
  assertValidOffset();
}
