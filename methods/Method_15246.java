/** 
 * start refresh
 * @author Lemon
 */
public void onRefresh(){
  showFooter(false);
  mPullRefreshing=true;
  smoothScrollToPosition(0);
  mHeaderView.setVisiableHeight((int)getContext().getResources().getDimension(R.dimen.lv_refresh_header_view_height));
  mHeaderView.setState(XListViewHeader.STATE_REFRESHING);
  if (mListViewListener != null) {
    mListViewListener.onRefresh();
  }
  resetHeaderHeight();
}
