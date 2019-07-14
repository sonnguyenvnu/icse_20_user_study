@Nullable private static Metadata parseIlst(ParsableByteArray ilst,int limit){
  ilst.skipBytes(Atom.HEADER_SIZE);
  ArrayList<Metadata.Entry> entries=new ArrayList<>();
  while (ilst.getPosition() < limit) {
    Metadata.Entry entry=MetadataUtil.parseIlstElement(ilst);
    if (entry != null) {
      entries.add(entry);
    }
  }
  return entries.isEmpty() ? null : new Metadata(entries);
}
