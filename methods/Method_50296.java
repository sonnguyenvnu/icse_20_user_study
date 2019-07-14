/** 
 * ?????
 */
public void setHasLoadMore(boolean hasLoadMore){
  mHasLoadMore=hasLoadMore;
  if (!mHasLoadMore) {
    showNoMoreUI();
  }
 else {
    showNormalUI();
  }
}
