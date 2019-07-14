/** 
 * Reads up to 32 bits.
 * @param numBits The number of bits to read.
 * @return An integer whose bottom {@code numBits} bits hold the read data.
 */
public int readBits(int numBits){
  int tempByteOffset=byteOffset;
  int bitsRead=Math.min(numBits,8 - bitOffset);
  int returnValue=((data[tempByteOffset++] & 0xFF) >> bitOffset) & (0xFF >> (8 - bitsRead));
  while (bitsRead < numBits) {
    returnValue|=(data[tempByteOffset++] & 0xFF) << bitsRead;
    bitsRead+=8;
  }
  returnValue&=0xFFFFFFFF >>> (32 - numBits);
  skipBits(numBits);
  return returnValue;
}
