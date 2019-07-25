@Override protected boolean contains(BitmapContainer bitmapContainer){
  final int cardinality=getCardinality();
  if (bitmapContainer.getCardinality() != -1 && bitmapContainer.getCardinality() > cardinality) {
    return false;
  }
  final int runCount=numberOfRuns();
  short ib=0, ir=0;
  while (ib < bitmapContainer.bitmap.length && ir < runCount) {
    long w=bitmapContainer.bitmap[ib];
    while (w != 0 && ir < runCount) {
      int start=Util.toIntUnsigned(getValue(ir));
      int stop=start + toIntUnsigned(getLength(ir));
      long t=w & -w;
      long r=ib * 64 + Long.numberOfTrailingZeros(w);
      if (r < start) {
        return false;
      }
 else       if (r > stop) {
        ++ir;
      }
 else {
        w^=t;
      }
    }
    if (w == 0) {
      ++ib;
    }
 else {
      return false;
    }
  }
  if (ib < bitmapContainer.bitmap.length) {
    for (; ib < bitmapContainer.bitmap.length; ib++) {
      if (bitmapContainer.bitmap[ib] != 0) {
        return false;
      }
    }
  }
  return true;
}
