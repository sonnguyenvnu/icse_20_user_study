/** 
 * ????????? ???????????
 */
private void postDelayLoad(){
synchronized (this) {
    if (!mIsLoading) {
      mIsLoading=true;
      bindingView.listOne.postDelayed(this::onRefresh,150);
    }
  }
}
