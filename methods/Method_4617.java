/** 
 * Parses a udta atom.
 * @param udtaAtom The udta (user data) atom to decode.
 * @param isQuickTime True for QuickTime media. False otherwise.
 * @return Parsed metadata, or null.
 */
@Nullable public static Metadata parseUdta(Atom.LeafAtom udtaAtom,boolean isQuickTime){
  if (isQuickTime) {
    return null;
  }
  ParsableByteArray udtaData=udtaAtom.data;
  udtaData.setPosition(Atom.HEADER_SIZE);
  while (udtaData.bytesLeft() >= Atom.HEADER_SIZE) {
    int atomPosition=udtaData.getPosition();
    int atomSize=udtaData.readInt();
    int atomType=udtaData.readInt();
    if (atomType == Atom.TYPE_meta) {
      udtaData.setPosition(atomPosition);
      return parseUdtaMeta(udtaData,atomPosition + atomSize);
    }
    udtaData.setPosition(atomPosition + atomSize);
  }
  return null;
}
