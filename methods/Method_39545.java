/** 
 * Reads an unsigned short value in this  {@link ClassReader}. <i>This method is intended for {@link Attribute} sub classes, and is normally not needed by class generators or adapters.</i>
 * @param offset the start index of the value to be read in this {@link ClassReader}.
 * @return the read value.
 */
public int readUnsignedShort(final int offset){
  byte[] classBuffer=classFileBuffer;
  return ((classBuffer[offset] & 0xFF) << 8) | (classBuffer[offset + 1] & 0xFF);
}
