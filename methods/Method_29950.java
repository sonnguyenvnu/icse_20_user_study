private String makeUrl(){
  return DoubanUtils.makeBroadcastListUrl(mUser != null ? mUser.getUidOrId() : mUserIdOrUid,mTopic);
}
