/** 
 * <p>Initializes this  {@link PropertySet} instance from a bytearray. The method assumes that it has been checked already that the byte array indeed represents a property set stream. It does no more checks on its own.</p>
 * @param src Byte array containing the property set stream
 * @param offset The property set stream starts at this offsetfrom the beginning of <var>src</var>
 * @param length Length of the property set stream.
 * @throws UnsupportedEncodingException if HPSF does not (yet) support theproperty set's character encoding.
 */
private void init(final byte[] src,final int offset,final int length) throws UnsupportedEncodingException {
  int o=offset;
  byteOrder=LittleEndian.getUShort(src,o);
  o+=LittleEndian.SHORT_SIZE;
  format=LittleEndian.getUShort(src,o);
  o+=LittleEndian.SHORT_SIZE;
  osVersion=(int)LittleEndian.getUInt(src,o);
  o+=LittleEndian.INT_SIZE;
  classID=new ClassID(src,o);
  o+=ClassID.LENGTH;
  final int sectionCount=LittleEndian.getInt(src,o);
  o+=LittleEndian.INT_SIZE;
  if (sectionCount < 0)   throw new HPSFRuntimeException("Section count " + sectionCount + " is negative.");
  sections=new ArrayList<Section>(sectionCount);
  for (int i=0; i < sectionCount; i++) {
    final Section s=new Section(src,o);
    o+=ClassID.LENGTH + LittleEndian.INT_SIZE;
    sections.add(s);
  }
}
