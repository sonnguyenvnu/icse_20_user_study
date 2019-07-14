/** 
 * Parses the size of an expandable class, as specified by ISO 14496-1 subsection 8.3.3.
 */
private static int parseExpandableClassSize(ParsableByteArray data){
  int currentByte=data.readUnsignedByte();
  int size=currentByte & 0x7F;
  while ((currentByte & 0x80) == 0x80) {
    currentByte=data.readUnsignedByte();
    size=(size << 7) | (currentByte & 0x7F);
  }
  return size;
}
