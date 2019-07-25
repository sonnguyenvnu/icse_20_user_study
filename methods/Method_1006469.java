/** 
 * Deserialize (retrieve) this bitmap. Unlike RoaringBitmap, there is no specification for now: it may change from one java version to another, and from one RoaringBitmap version to another. The current bitmap is overwritten.
 * @param in the DataInput stream
 * @throws IOException Signals that an I/O exception has occurred.
 */
public void deserialize(DataInput in) throws IOException {
  this.clear();
  signedLongs=in.readBoolean();
  int nbHighs=in.readInt();
  if (signedLongs) {
    highToBitmap=new TreeMap<>();
  }
 else {
    highToBitmap=new TreeMap<>(RoaringIntPacking.unsignedComparator());
  }
  for (int i=0; i < nbHighs; i++) {
    int high=in.readInt();
    RoaringBitmap provider=new RoaringBitmap();
    provider.deserialize(in);
    highToBitmap.put(high,provider);
  }
  resetPerfHelpers();
}
