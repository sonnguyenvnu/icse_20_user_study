/** 
 * stop load more, reset footer view.
 * @param isHaveMore 
 */
public void stopLoadMore(boolean isHaveMore){
  if (mPullLoading == true) {
    mPullLoading=false;
    mFooterView.setState(isHaveMore ? XListViewFooter.STATE_NORMAL : XListViewFooter.STATE_COMPLETE);
  }
}
