public void trackPostedComment(final @NonNull Project project,final @Nullable Update update,final @NonNull KoalaContext.CommentDialog context){
  final User loggedInUser=this.client.loggedInUser();
  final Map<String,Object> props=update == null ? KoalaUtils.projectProperties(project,loggedInUser) : KoalaUtils.updateProperties(project,update,loggedInUser);
  props.put("context",context.getTrackingString());
  this.client.track(KoalaEvent.POSTED_COMMENT,props);
}
