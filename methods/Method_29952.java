@Override protected TimelineBroadcastListResource onAttachResource(){
  return TimelineBroadcastListResource.attachTo(mUserIdOrUid,mTopic,this);
}
