/** 
 * Reads a signed short value in this  {@link ClassReader}. <i>This method is intended for  {@link Attribute} sub classes, and is normally not needed by class generators or adapters.</i>
 * @param offset the start offset of the value to be read in this {@link ClassReader}.
 * @return the read value.
 */
public short readShort(final int offset){
  byte[] classBuffer=classFileBuffer;
  return (short)(((classBuffer[offset] & 0xFF) << 8) | (classBuffer[offset + 1] & 0xFF));
}
