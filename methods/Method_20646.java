public void trackOpenedExternalLink(final @NonNull Project project,final @NonNull KoalaContext.ExternalLink context){
  final Map<String,Object> props=KoalaUtils.projectProperties(project,this.client.loggedInUser());
  props.put("context",context.getTrackingString());
  this.client.track(KoalaEvent.OPENED_EXTERNAL_LINK,props);
}
