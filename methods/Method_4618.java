/** 
 * Parses a metadata meta atom if it contains metadata with handler 'mdta'.
 * @param meta The metadata atom to decode.
 * @return Parsed metadata, or null.
 */
@Nullable public static Metadata parseMdtaFromMeta(Atom.ContainerAtom meta){
  Atom.LeafAtom hdlrAtom=meta.getLeafAtomOfType(Atom.TYPE_hdlr);
  Atom.LeafAtom keysAtom=meta.getLeafAtomOfType(Atom.TYPE_keys);
  Atom.LeafAtom ilstAtom=meta.getLeafAtomOfType(Atom.TYPE_ilst);
  if (hdlrAtom == null || keysAtom == null || ilstAtom == null || AtomParsers.parseHdlr(hdlrAtom.data) != TYPE_mdta) {
    return null;
  }
  ParsableByteArray keys=keysAtom.data;
  keys.setPosition(Atom.FULL_HEADER_SIZE);
  int entryCount=keys.readInt();
  String[] keyNames=new String[entryCount];
  for (int i=0; i < entryCount; i++) {
    int entrySize=keys.readInt();
    keys.skipBytes(4);
    int keySize=entrySize - 8;
    keyNames[i]=keys.readString(keySize);
  }
  ParsableByteArray ilst=ilstAtom.data;
  ilst.setPosition(Atom.HEADER_SIZE);
  ArrayList<Metadata.Entry> entries=new ArrayList<>();
  while (ilst.bytesLeft() > Atom.HEADER_SIZE) {
    int atomPosition=ilst.getPosition();
    int atomSize=ilst.readInt();
    int keyIndex=ilst.readInt() - 1;
    if (keyIndex >= 0 && keyIndex < keyNames.length) {
      String key=keyNames[keyIndex];
      Metadata.Entry entry=MetadataUtil.parseMdtaMetadataEntryFromIlst(ilst,atomPosition + atomSize,key);
      if (entry != null) {
        entries.add(entry);
      }
    }
 else {
      Log.w(TAG,"Skipped metadata with unknown key index: " + keyIndex);
    }
    ilst.setPosition(atomPosition + atomSize);
  }
  return entries.isEmpty() ? null : new Metadata(entries);
}
