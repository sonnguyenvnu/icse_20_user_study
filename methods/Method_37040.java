public boolean requirePlaceholderCell(){
  return mPlaceholderRequired && mPlaceholderCell != null && !TextUtils.isEmpty(load) && (mCells.size() == 0 || (mCells.size() == 1 && mCells.contains(mPlaceholderCell)));
}
