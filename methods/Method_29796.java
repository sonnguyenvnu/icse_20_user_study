@Override protected ApiRequest<List<Broadcast>> onCreateRequest(boolean more,int count){
  Long untilId=null;
  if (more && has()) {
    List<Broadcast> broadcastList=get();
    int size=broadcastList.size();
    if (size > 0) {
      untilId=broadcastList.get(size - 1).id;
    }
  }
  return ApiService.getInstance().getApiV2BroadcastList(mUserIdOrUid,mTopic,untilId,count);
}
