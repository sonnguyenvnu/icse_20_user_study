public void trackProjectStar(final @NonNull Project project){
  final Map<String,Object> props=KoalaUtils.projectProperties(project,this.client.loggedInUser());
  this.client.track(project.isStarred() ? KoalaEvent.PROJECT_STAR : KoalaEvent.PROJECT_UNSTAR,props);
  this.client.track(project.isStarred() ? KoalaEvent.STARRED_PROJECT : KoalaEvent.UNSTARRED_PROJECT,props);
}
