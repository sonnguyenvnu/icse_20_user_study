@Override public boolean containsUri(Uri uri){
  for (int i=0; i < mCacheKeys.size(); i++) {
    if (mCacheKeys.get(i).containsUri(uri)) {
      return true;
    }
  }
  return false;
}
