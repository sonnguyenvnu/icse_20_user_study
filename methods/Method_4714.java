/** 
 * Reads an int of  {@code length} bits from {@code src} starting at {@code leastSignificantBitIndex}.
 * @param src the {@code byte} to read from.
 * @param length the length in bits of the int to read.
 * @param leastSignificantBitIndex the index of the least significant bit of the int to read.
 * @return the int value read.
 */
@VisibleForTesting static int readBits(byte src,int length,int leastSignificantBitIndex){
  return (src >> leastSignificantBitIndex) & (255 >>> (8 - length));
}
