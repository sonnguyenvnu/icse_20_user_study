private static TimelineBroadcastListResource newInstance(String userIdOrUid,String topic){
  return new TimelineBroadcastListResource().setArguments(userIdOrUid,topic);
}
