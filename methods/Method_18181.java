@Override public boolean isEquivalentTo(ComparableDrawable other){
  if (this == other) {
    return true;
  }
  if (!(other instanceof ComparableColorDrawable)) {
    return false;
  }
  return mColor == ((ComparableColorDrawable)other).mColor;
}
