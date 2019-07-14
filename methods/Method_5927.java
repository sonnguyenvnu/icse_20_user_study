/** 
 * Reads a single bit.
 * @return Whether the bit is set.
 */
public boolean readBit(){
  boolean returnValue=(data[byteOffset] & (0x80 >> bitOffset)) != 0;
  skipBit();
  return returnValue;
}
