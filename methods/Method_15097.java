/** 
 * ??????
 * @param page
 * @param isCache
 */
private synchronized void stopLoadData(int page,boolean isCache){
  Log.i(TAG,"stopLoadData  isCache = " + isCache);
  isLoading=false;
  dismissProgressDialog();
  if (isCache) {
    Log.d(TAG,"stopLoadData  isCache >> return;");
    return;
  }
  if (onStopLoadListener == null) {
    Log.w(TAG,"stopLoadData  onStopLoadListener == null >> return;");
    return;
  }
  onStopLoadListener.onStopRefresh();
  if (page > HttpManager.PAGE_NUM_0) {
    onStopLoadListener.onStopLoadMore(isHaveMore);
  }
}
