/** 
 * Parses an mdhd atom (defined in 14496-12).
 * @param mdhd The mdhd atom to decode.
 * @return A pair consisting of the media timescale defined as the number of time units that passin one second, and the language code.
 */
private static Pair<Long,String> parseMdhd(ParsableByteArray mdhd){
  mdhd.setPosition(Atom.HEADER_SIZE);
  int fullAtom=mdhd.readInt();
  int version=Atom.parseFullAtomVersion(fullAtom);
  mdhd.skipBytes(version == 0 ? 8 : 16);
  long timescale=mdhd.readUnsignedInt();
  mdhd.skipBytes(version == 0 ? 4 : 8);
  int languageCode=mdhd.readUnsignedShort();
  String language="" + (char)(((languageCode >> 10) & 0x1F) + 0x60) + (char)(((languageCode >> 5) & 0x1F) + 0x60) + (char)((languageCode & 0x1F) + 0x60);
  return Pair.create(timescale,language);
}
