/** 
 * Returns the offset in  {@link #classFileBuffer} of the first ClassFile's 'attributes' arrayfield entry.
 * @return the offset in {@link #classFileBuffer} of the first ClassFile's 'attributes' arrayfield entry.
 */
final int getFirstAttributeOffset(){
  int currentOffset=header + 8 + readUnsignedShort(header + 6) * 2;
  int fieldsCount=readUnsignedShort(currentOffset);
  currentOffset+=2;
  while (fieldsCount-- > 0) {
    int attributesCount=readUnsignedShort(currentOffset + 6);
    currentOffset+=8;
    while (attributesCount-- > 0) {
      currentOffset+=6 + readInt(currentOffset + 2);
    }
  }
  int methodsCount=readUnsignedShort(currentOffset);
  currentOffset+=2;
  while (methodsCount-- > 0) {
    int attributesCount=readUnsignedShort(currentOffset + 6);
    currentOffset+=8;
    while (attributesCount-- > 0) {
      currentOffset+=6 + readInt(currentOffset + 2);
    }
  }
  return currentOffset + 2;
}
