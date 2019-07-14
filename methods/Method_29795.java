protected ApiV2BroadcastListResource setArguments(String userIdOrUid,String topic){
  FragmentUtils.getArgumentsBuilder(this).putString(EXTRA_USER_ID_OR_UID,userIdOrUid).putString(EXTRA_TOPIC,topic);
  return this;
}
