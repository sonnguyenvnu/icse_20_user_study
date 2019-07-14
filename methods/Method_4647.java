@Nullable private static Id3Frame parseInternalAttribute(ParsableByteArray data,int endPosition){
  String domain=null;
  String name=null;
  int dataAtomPosition=-1;
  int dataAtomSize=-1;
  while (data.getPosition() < endPosition) {
    int atomPosition=data.getPosition();
    int atomSize=data.readInt();
    int atomType=data.readInt();
    data.skipBytes(4);
    if (atomType == Atom.TYPE_mean) {
      domain=data.readNullTerminatedString(atomSize - 12);
    }
 else     if (atomType == Atom.TYPE_name) {
      name=data.readNullTerminatedString(atomSize - 12);
    }
 else {
      if (atomType == Atom.TYPE_data) {
        dataAtomPosition=atomPosition;
        dataAtomSize=atomSize;
      }
      data.skipBytes(atomSize - 12);
    }
  }
  if (domain == null || name == null || dataAtomPosition == -1) {
    return null;
  }
  data.setPosition(dataAtomPosition);
  data.skipBytes(16);
  String value=data.readNullTerminatedString(dataAtomSize - 16);
  return new InternalFrame(domain,name,value);
}
