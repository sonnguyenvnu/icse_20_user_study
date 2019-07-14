/** 
 * Overwrites  {@code numBits} from this array using the {@code numBits} least significant bitsfrom  {@code value}. Bits are written in order from most significant to least significant. The read position is advanced by  {@code numBits}.
 * @param value The integer whose {@code numBits} least significant bits are written into {@link #data}.
 * @param numBits The number of bits to write.
 */
public void putInt(int value,int numBits){
  int remainingBitsToRead=numBits;
  if (numBits < 32) {
    value&=(1 << numBits) - 1;
  }
  int firstByteReadSize=Math.min(8 - bitOffset,numBits);
  int firstByteRightPaddingSize=8 - bitOffset - firstByteReadSize;
  int firstByteBitmask=(0xFF00 >> bitOffset) | ((1 << firstByteRightPaddingSize) - 1);
  data[byteOffset]=(byte)(data[byteOffset] & firstByteBitmask);
  int firstByteInputBits=value >>> (numBits - firstByteReadSize);
  data[byteOffset]=(byte)(data[byteOffset] | (firstByteInputBits << firstByteRightPaddingSize));
  remainingBitsToRead-=firstByteReadSize;
  int currentByteIndex=byteOffset + 1;
  while (remainingBitsToRead > 8) {
    data[currentByteIndex++]=(byte)(value >>> (remainingBitsToRead - 8));
    remainingBitsToRead-=8;
  }
  int lastByteRightPaddingSize=8 - remainingBitsToRead;
  data[currentByteIndex]=(byte)(data[currentByteIndex] & ((1 << lastByteRightPaddingSize) - 1));
  int lastByteInput=value & ((1 << remainingBitsToRead) - 1);
  data[currentByteIndex]=(byte)(data[currentByteIndex] | (lastByteInput << lastByteRightPaddingSize));
  skipBits(numBits);
  assertValidOffset();
}
