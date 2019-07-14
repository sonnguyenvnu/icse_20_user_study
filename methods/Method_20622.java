public void trackViewedComments(final @NonNull Project project,final @Nullable Update update,final @NonNull KoalaContext.Comments context){
  final User loggedInUser=this.client.loggedInUser();
  final Map<String,Object> props=update == null ? KoalaUtils.projectProperties(project,loggedInUser) : KoalaUtils.updateProperties(project,update,loggedInUser);
  props.put("context",context.getTrackingString());
  this.client.track(KoalaEvent.VIEWED_COMMENTS,props);
}
