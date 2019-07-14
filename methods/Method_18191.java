@Override public boolean isEquivalentTo(ComparableDrawable other){
  if (this == other) {
    return true;
  }
  if (!(other instanceof ComparableResDrawable)) {
    return false;
  }
  return mResId == ((ComparableResDrawable)other).mResId && mConfig.equals(((ComparableResDrawable)other).mConfig);
}
