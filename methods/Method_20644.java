public void trackViewedUpdate(final @NonNull Project project,final @NonNull KoalaContext.Update context){
  final Map<String,Object> props=KoalaUtils.projectProperties(project,this.client.loggedInUser());
  props.put("context",context.getTrackingString());
  this.client.track(KoalaEvent.VIEWED_UPDATE,props);
}
