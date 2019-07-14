@Override public boolean isEquivalentTo(ComparableDrawable other){
  if (this == other) {
    return true;
  }
  if (!(other instanceof ComparableIntIdDrawable)) {
    return false;
  }
  return mId == ((ComparableIntIdDrawable)other).mId;
}
