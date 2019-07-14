@Nullable private static Metadata parseUdtaMeta(ParsableByteArray meta,int limit){
  meta.skipBytes(Atom.FULL_HEADER_SIZE);
  while (meta.getPosition() < limit) {
    int atomPosition=meta.getPosition();
    int atomSize=meta.readInt();
    int atomType=meta.readInt();
    if (atomType == Atom.TYPE_ilst) {
      meta.setPosition(atomPosition);
      return parseIlst(meta,atomPosition + atomSize);
    }
    meta.setPosition(atomPosition + atomSize);
  }
  return null;
}
