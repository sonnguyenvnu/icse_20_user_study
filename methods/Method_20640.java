public void trackCheckoutFinishJumpToProject(final @NonNull Project project){
  final Map<String,Object> props=KoalaUtils.projectProperties(project,this.client.loggedInUser());
  this.client.track("Checkout Finished Discover Open Project",props);
}
