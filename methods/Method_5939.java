/** 
 * Reads the next  {@code length} bytes into {@code bitArray}, and resets the position of {@code bitArray} to zero.
 * @param bitArray The {@link ParsableBitArray} into which the bytes should be read.
 * @param length The number of bytes to write.
 */
public void readBytes(ParsableBitArray bitArray,int length){
  readBytes(bitArray.data,0,length);
  bitArray.setPosition(0);
}
