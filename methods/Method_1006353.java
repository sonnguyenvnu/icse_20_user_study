@Override protected boolean contains(BitmapContainer bitmapContainer){
  if ((cardinality != -1) && (bitmapContainer.cardinality != -1)) {
    if (cardinality < bitmapContainer.cardinality) {
      return false;
    }
  }
  for (int i=0; i < bitmapContainer.bitmap.length; ++i) {
    if ((this.bitmap[i] & bitmapContainer.bitmap[i]) != bitmapContainer.bitmap[i]) {
      return false;
    }
  }
  return true;
}
