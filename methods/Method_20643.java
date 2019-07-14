public void trackVideoStart(final @NonNull Project project){
  this.client.track("Project Video Start",KoalaUtils.projectProperties(project,this.client.loggedInUser()));
}
