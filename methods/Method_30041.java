private void loadFromCache(){
  mLoadingFromCache=true;
  mAccount=AccountUtils.getActiveAccount();
  NotificationCountCache.get(mAccount,mHandler,this::onLoadFromCacheFinished,getActivity());
  onLoadStarted();
}
