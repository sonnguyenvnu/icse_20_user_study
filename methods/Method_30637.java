public void notifyChangedIfLoaded(){
  getListener().onChanged(getRequestCode(),getUser(),mBroadcastListResource != null ? mBroadcastListResource.get() : null,mFollowingListResource != null ? mFollowingListResource.get() : null,mDiaryListResource != null ? mDiaryListResource.get() : null,mUserItemListResource != null ? mUserItemListResource.get() : null,mReviewListResource != null ? mReviewListResource.get() : null);
}
