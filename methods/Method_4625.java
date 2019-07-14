/** 
 * Parses the edts atom (defined in 14496-12 subsection 8.6.5).
 * @param edtsAtom edts (edit box) atom to decode.
 * @return Pair of edit list durations and edit list media times, or a pair of nulls if they arenot present.
 */
private static Pair<long[],long[]> parseEdts(Atom.ContainerAtom edtsAtom){
  Atom.LeafAtom elst;
  if (edtsAtom == null || (elst=edtsAtom.getLeafAtomOfType(Atom.TYPE_elst)) == null) {
    return Pair.create(null,null);
  }
  ParsableByteArray elstData=elst.data;
  elstData.setPosition(Atom.HEADER_SIZE);
  int fullAtom=elstData.readInt();
  int version=Atom.parseFullAtomVersion(fullAtom);
  int entryCount=elstData.readUnsignedIntToInt();
  long[] editListDurations=new long[entryCount];
  long[] editListMediaTimes=new long[entryCount];
  for (int i=0; i < entryCount; i++) {
    editListDurations[i]=version == 1 ? elstData.readUnsignedLongToLong() : elstData.readUnsignedInt();
    editListMediaTimes[i]=version == 1 ? elstData.readLong() : elstData.readInt();
    int mediaRateInteger=elstData.readShort();
    if (mediaRateInteger != 1) {
      throw new IllegalArgumentException("Unsupported media rate.");
    }
    elstData.skipBytes(2);
  }
  return Pair.create(editListDurations,editListMediaTimes);
}
