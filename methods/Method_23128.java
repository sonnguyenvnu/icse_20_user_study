private void writeEpilog() throws IOException {
  Date modificationTime=new Date();
  long duration=getMovieDuration();
  DataAtom leaf;
  moovAtom=new CompositeAtom("moov");
  leaf=new DataAtom("mvhd");
  moovAtom.add(leaf);
  DataAtomOutputStream d=leaf.getOutputStream();
  d.writeByte(0);
  d.writeByte(0);
  d.writeByte(0);
  d.writeByte(0);
  d.writeMacTimestamp(creationTime);
  d.writeMacTimestamp(modificationTime);
  d.writeUInt(movieTimeScale);
  d.writeUInt(duration);
  d.writeFixed16D16(1d);
  d.writeShort(256);
  d.write(new byte[10]);
  d.writeFixed16D16(1f);
  d.writeFixed16D16(0f);
  d.writeFixed2D30(0f);
  d.writeFixed16D16(0f);
  d.writeFixed16D16(1f);
  d.writeFixed2D30(0);
  d.writeFixed16D16(0);
  d.writeFixed16D16(0);
  d.writeFixed2D30(1f);
  d.writeInt(0);
  d.writeInt(0);
  d.writeInt(0);
  d.writeInt(0);
  d.writeInt(0);
  d.writeInt(0);
  d.writeUInt(tracks.size() + 1);
  for (int i=0, n=tracks.size(); i < n; i++) {
    Track t=tracks.get(i);
    t.writeTrackAtoms(i,moovAtom,modificationTime);
  }
  moovAtom.finish();
}
