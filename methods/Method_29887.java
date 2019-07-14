public static TimelineBroadcastListResource attachTo(String userIdOrUid,String topic,Fragment fragment){
  return attachTo(userIdOrUid,topic,fragment,FRAGMENT_TAG_DEFAULT,REQUEST_CODE_INVALID);
}
