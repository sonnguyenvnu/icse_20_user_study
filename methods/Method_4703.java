/** 
 * Reads a single bit.
 * @return {@code true} if the bit is set, {@code false} otherwise.
 */
public boolean readBit(){
  boolean returnValue=(((data[byteOffset] & 0xFF) >> bitOffset) & 0x01) == 1;
  skipBits(1);
  return returnValue;
}
