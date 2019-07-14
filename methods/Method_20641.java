public void trackSentMessage(final @NonNull Project project,final @NonNull KoalaContext.Message context){
  final Map<String,Object> props=KoalaUtils.projectProperties(project,this.client.loggedInUser());
  props.put("context",context.getTrackingString());
  this.client.track(KoalaEvent.SENT_MESSAGE,props);
}
