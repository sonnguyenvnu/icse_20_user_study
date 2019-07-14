@Override protected ApiRequest<TimelineList> onCreateRequest(boolean more,int count){
  Long untilId=null;
  if (more && has()) {
    List<Broadcast> broadcastList=get();
    int size=broadcastList.size();
    if (size > 0) {
      untilId=broadcastList.get(size - 1).id;
    }
  }
  return ApiService.getInstance().getTimelineList(mUserIdOrUid,mTopic,untilId,count);
}
