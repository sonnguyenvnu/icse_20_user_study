@Override public boolean hasBorderColor(){
  for (  int color : mBorderColors) {
    if (color != Color.TRANSPARENT) {
      return true;
    }
  }
  return false;
}
