/** 
 * ????
 */
private void executeLoadMore(){
  if (!mLoadMoreLock && mHasLoadMore) {
    if (mOnLoadMoreListener != null) {
      mOnLoadMoreListener.loadMore();
    }
    mLoadMoreLock=true;
    showLoadingUI();
  }
}
