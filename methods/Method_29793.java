private static ApiV2BroadcastListResource newInstance(String userIdOrUid,String topic){
  return new ApiV2BroadcastListResource().setArguments(userIdOrUid,topic);
}
