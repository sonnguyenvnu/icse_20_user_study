private void loadFromCache(){
  mLoadingFromCache=true;
  mAccount=AccountUtils.getActiveAccount();
  NotificationListCache.get(mAccount,mHandler,this::onLoadFromCacheFinished,getActivity());
  onLoadStarted();
}
