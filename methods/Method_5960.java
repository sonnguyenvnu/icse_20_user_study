/** 
 * Skips a single bit.
 */
public void skipBit(){
  if (++bitOffset == 8) {
    bitOffset=0;
    byteOffset+=shouldSkipByte(byteOffset + 1) ? 2 : 1;
  }
  assertValidOffset();
}
