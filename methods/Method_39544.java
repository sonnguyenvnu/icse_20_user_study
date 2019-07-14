/** 
 * Reads a byte value in this  {@link ClassReader}. <i>This method is intended for  {@link Attribute} sub classes, and is normally not needed by class generators or adapters.</i>
 * @param offset the start offset of the value to be read in this {@link ClassReader}.
 * @return the read value.
 */
public int readByte(final int offset){
  return classFileBuffer[offset] & 0xFF;
}
