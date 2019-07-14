/** 
 * @return true if the data fetching has been completed. 
 */
@UiThread boolean isLoadingCompleted(){
  return mLoadingState == null || mLoadingState == LoadingEvent.LoadingState.FAILED || mLoadingState == LoadingEvent.LoadingState.SUCCEEDED;
}
