/** 
 * @deprecated Use {@link #trackViewedComments(Project,Update,KoalaContext.Comments)} instead.
 */
@Deprecated public void trackProjectCommentsView(final @NonNull Project project){
  this.client.track(KoalaEvent.PROJECT_COMMENT_VIEW,KoalaUtils.projectProperties(project,this.client.loggedInUser()));
}
