@Override public boolean intersects(BitmapContainer value2){
  for (int k=0; k < this.bitmap.length; ++k) {
    if ((this.bitmap[k] & value2.bitmap[k]) != 0) {
      return true;
    }
  }
  return false;
}
