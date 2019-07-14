public boolean isAnyLoaded(){
  return hasUser() || (mBroadcastListResource != null && mBroadcastListResource.has()) || (mFollowingListResource != null && mFollowingListResource.has()) || (mDiaryListResource != null && mDiaryListResource.has()) || (mUserItemListResource != null && mUserItemListResource.has()) || (mReviewListResource != null && mReviewListResource.has());
}
