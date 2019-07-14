public void trackSwitchedProjects(final @NonNull Project project){
  final Map<String,Object> properties=KoalaUtils.projectProperties(project,this.client.loggedInUser());
  this.client.track(KoalaEvent.SWITCHED_PROJECTS,properties);
}
