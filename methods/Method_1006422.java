@Override protected boolean contains(MappeableBitmapContainer bitmapContainer){
  if ((cardinality != -1) && (bitmapContainer.cardinality != -1)) {
    if (cardinality < bitmapContainer.cardinality) {
      return false;
    }
  }
  for (int i=0; i < MAX_CAPACITY / 64; ++i) {
    if ((this.bitmap.get(i) & bitmapContainer.bitmap.get(i)) != bitmapContainer.bitmap.get(i)) {
      return false;
    }
  }
  return true;
}
