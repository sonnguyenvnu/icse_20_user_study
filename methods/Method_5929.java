/** 
 * Reads  {@code numBits} bits into {@code buffer}.
 * @param buffer The array into which the read data should be written. The trailing{@code numBits % 8} bits are written into the most significant bits of the last modified{@code buffer} byte. The remaining ones are unmodified.
 * @param offset The offset in {@code buffer} at which the read data should be written.
 * @param numBits The number of bits to read.
 */
public void readBits(byte[] buffer,int offset,int numBits){
  int to=offset + (numBits >> 3);
  for (int i=offset; i < to; i++) {
    buffer[i]=(byte)(data[byteOffset++] << bitOffset);
    buffer[i]=(byte)(buffer[i] | ((data[byteOffset] & 0xFF) >> (8 - bitOffset)));
  }
  int bitsLeft=numBits & 7;
  if (bitsLeft == 0) {
    return;
  }
  buffer[to]=(byte)(buffer[to] & (0xFF >> bitsLeft));
  if (bitOffset + bitsLeft > 8) {
    buffer[to]=(byte)(buffer[to] | ((data[byteOffset++] & 0xFF) << bitOffset));
    bitOffset-=8;
  }
  bitOffset+=bitsLeft;
  int lastDataByteTrailingBits=(data[byteOffset] & 0xFF) >> (8 - bitOffset);
  buffer[to]|=(byte)(lastDataByteTrailingBits << (8 - bitsLeft));
  if (bitOffset == 8) {
    bitOffset=0;
    byteOffset++;
  }
  assertValidOffset();
}
