@Override public int getItemCount(){
  int count;
  if (mLoading) {
    count=mData.size() + 1 + getHeaderViewCount();
  }
 else {
    count=mData.size() + getHeaderViewCount() + getFooterViewCount();
  }
  mEmptyEnable=false;
  if (count == 0) {
    mEmptyEnable=true;
    count+=getEmptyViewCount();
  }
  return count;
}
