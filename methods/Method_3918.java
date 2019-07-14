public View getAttachedScrapChildAt(int index){
  if (index < 0 || index >= mRecycler.mAttachedScrap.size()) {
    return null;
  }
  return mRecycler.getScrapViewAt(index);
}
