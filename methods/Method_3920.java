public View getCachedChildAt(int index){
  if (index < 0 || index >= mRecycler.mCachedViews.size()) {
    return null;
  }
  return mRecycler.mCachedViews.get(index).itemView;
}
