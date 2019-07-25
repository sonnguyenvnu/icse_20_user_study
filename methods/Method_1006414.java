@Override public MappeableContainer limit(int maxcardinality){
  if (maxcardinality >= this.cardinality) {
    return clone();
  }
  if (maxcardinality <= MappeableArrayContainer.DEFAULT_MAX_SIZE) {
    MappeableArrayContainer ac=new MappeableArrayContainer(maxcardinality);
    int pos=0;
    if (!BufferUtil.isBackedBySimpleArray(ac.content)) {
      throw new RuntimeException("Should not happen. Internal bug.");
    }
    short[] cont=ac.content.array();
    int len=this.bitmap.limit();
    for (int k=0; (ac.cardinality < maxcardinality) && (k < len); ++k) {
      long bitset=bitmap.get(k);
      while ((ac.cardinality < maxcardinality) && (bitset != 0)) {
        cont[pos++]=(short)(k * 64 + numberOfTrailingZeros(bitset));
        ac.cardinality++;
        bitset&=(bitset - 1);
      }
    }
    return ac;
  }
  MappeableBitmapContainer bc=new MappeableBitmapContainer(maxcardinality,this.bitmap);
  int s=toIntUnsigned(select(maxcardinality));
  int usedwords=(s + 63) / 64;
  int len=this.bitmap.limit();
  int todelete=len - usedwords;
  for (int k=0; k < todelete; ++k) {
    bc.bitmap.put(len - 1 - k,0);
  }
  int lastword=s % 64;
  if (lastword != 0) {
    bc.bitmap.put(s / 64,(bc.bitmap.get(s / 64) & (0xFFFFFFFFFFFFFFFFL >>> (64 - lastword))));
  }
  return bc;
}
