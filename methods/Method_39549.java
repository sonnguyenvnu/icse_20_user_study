/** 
 * Reads a CONSTANT_Class, CONSTANT_String, CONSTANT_MethodType, CONSTANT_Module or CONSTANT_Package constant pool entry in  {@link #classFileBuffer}. <i>This method is intended for  {@link Attribute} sub classes, and is normally not needed by class generators oradapters.</i>
 * @param offset the start offset of an unsigned short value in {@link #classFileBuffer}, whose value is the index of a CONSTANT_Class, CONSTANT_String, CONSTANT_MethodType, CONSTANT_Module or CONSTANT_Package entry in class's constant pool table.
 * @param charBuffer the buffer to be used to read the item. This buffer must be sufficientlylarge. It is not automatically resized.
 * @return the String corresponding to the specified constant pool entry.
 */
private String readStringish(final int offset,final char[] charBuffer){
  return readUTF8(cpInfoOffsets[readUnsignedShort(offset)],charBuffer);
}
