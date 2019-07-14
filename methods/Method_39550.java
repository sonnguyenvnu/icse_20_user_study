/** 
 * Reads a CONSTANT_Class constant pool entry in this  {@link ClassReader}. <i>This method is intended for  {@link Attribute} sub classes, and is normally not needed by class generators oradapters.</i>
 * @param offset the start offset of an unsigned short value in this {@link ClassReader}, whose value is the index of a CONSTANT_Class entry in class's constant pool table.
 * @param charBuffer the buffer to be used to read the item. This buffer must be sufficientlylarge. It is not automatically resized.
 * @return the String corresponding to the specified CONSTANT_Class entry.
 */
public String readClass(final int offset,final char[] charBuffer){
  return readStringish(offset,charBuffer);
}
