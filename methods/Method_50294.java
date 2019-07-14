/** 
 * ?????
 */
private void showNoMoreUI(){
  mLoadMoreLock=false;
  mPbLoading.setVisibility(View.GONE);
  mTvMessage.setText(R.string.gallery_loading_view_no_more);
}
