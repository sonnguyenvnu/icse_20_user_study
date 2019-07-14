@Override public boolean isEquivalentTo(ComparableDrawable other){
  if (this == other) {
    return true;
  }
  if (!(other instanceof DefaultComparableDrawable)) {
    return false;
  }
  return getWrappedDrawable().equals(((DefaultComparableDrawable)other).getWrappedDrawable());
}
