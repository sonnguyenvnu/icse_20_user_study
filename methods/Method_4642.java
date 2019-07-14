/** 
 * Parses an 'mdta' metadata entry starting at the current position in an ilst box.
 * @param ilst The ilst box.
 * @param endPosition The end position of the entry in the ilst box.
 * @param key The mdta metadata entry key for the entry.
 * @return The parsed element, or null if the entry wasn't recognized.
 */
@Nullable public static MdtaMetadataEntry parseMdtaMetadataEntryFromIlst(ParsableByteArray ilst,int endPosition,String key){
  int atomPosition;
  while ((atomPosition=ilst.getPosition()) < endPosition) {
    int atomSize=ilst.readInt();
    int atomType=ilst.readInt();
    if (atomType == Atom.TYPE_data) {
      int typeIndicator=ilst.readInt();
      int localeIndicator=ilst.readInt();
      int dataSize=atomSize - 16;
      byte[] value=new byte[dataSize];
      ilst.readBytes(value,0,dataSize);
      return new MdtaMetadataEntry(key,value,localeIndicator,typeIndicator);
    }
    ilst.setPosition(atomPosition + atomSize);
  }
  return null;
}
