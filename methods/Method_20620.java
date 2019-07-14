/** 
 * @deprecated Use {@link #trackPostedComment(Project,Update,KoalaContext.CommentDialog)} instead.
 */
@Deprecated public void trackProjectCommentCreate(final @NonNull Project project){
  this.client.track(KoalaEvent.PROJECT_COMMENT_CREATE,KoalaUtils.projectProperties(project,this.client.loggedInUser()));
}
