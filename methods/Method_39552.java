/** 
 * Reads a CONSTANT_Package constant pool entry in this  {@link ClassReader}. <i>This method is intended for  {@link Attribute} sub classes, and is normally not needed by class generators oradapters.</i>
 * @param offset the start offset of an unsigned short value in this {@link ClassReader}, whose value is the index of a CONSTANT_Package entry in class's constant pool table.
 * @param charBuffer the buffer to be used to read the item. This buffer must be sufficientlylarge. It is not automatically resized.
 * @return the String corresponding to the specified CONSTANT_Package entry.
 */
public String readPackage(final int offset,final char[] charBuffer){
  return readStringish(offset,charBuffer);
}
