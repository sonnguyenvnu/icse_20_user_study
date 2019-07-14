/** 
 * Parses a mvhd atom (defined in 14496-12), returning the timescale for the movie.
 * @param mvhd Contents of the mvhd atom to be parsed.
 * @return Timescale for the movie.
 */
private static long parseMvhd(ParsableByteArray mvhd){
  mvhd.setPosition(Atom.HEADER_SIZE);
  int fullAtom=mvhd.readInt();
  int version=Atom.parseFullAtomVersion(fullAtom);
  mvhd.skipBytes(version == 0 ? 8 : 16);
  return mvhd.readUnsignedInt();
}
