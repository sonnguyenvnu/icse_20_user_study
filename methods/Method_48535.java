/** 
 * The format used is this: - The first bit indicates whether this is the first block (reading backwards, this would be the stop criterion) - In the first byte, the 3 bits after the first bit indicate the number of bytes written minus 3 (since 3 is the minimum number of bytes written. So, if the 3 bits are 010 = 2 => 5 bytes written. The value is aligned to the left to ensure that this encoding is byte order preserving.
 * @param out
 * @param value
 */
private static void writeUnsignedBackward(WriteBuffer out,final long value){
  int numBytes=unsignedBackwardLength(value);
  int prefixLen=numBytes - 3;
  assert prefixLen >= 0 && prefixLen < 8;
  byte b=(byte)((prefixLen << 4) | 0x80);
  for (int i=numBytes - 1; i >= 0; i--) {
    b=(byte)(b | (0x7F & (value >>> (i * 7))));
    out.putByte(b);
    b=0;
  }
}
