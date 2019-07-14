/** 
 * Returns whether it's possible to read  {@code n} bits starting from the current offset. Theoffset is not modified.
 * @param numBits The number of bits.
 * @return Whether it is possible to read {@code n} bits.
 */
public boolean canReadBits(int numBits){
  int oldByteOffset=byteOffset;
  int numBytes=numBits / 8;
  int newByteOffset=byteOffset + numBytes;
  int newBitOffset=bitOffset + numBits - (numBytes * 8);
  if (newBitOffset > 7) {
    newByteOffset++;
    newBitOffset-=8;
  }
  for (int i=oldByteOffset + 1; i <= newByteOffset && newByteOffset < byteLimit; i++) {
    if (shouldSkipByte(i)) {
      newByteOffset++;
      i+=2;
    }
  }
  return newByteOffset < byteLimit || (newByteOffset == byteLimit && newBitOffset == 0);
}
