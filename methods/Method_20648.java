public void trackViewedProjectDashboard(final @NonNull Project project){
  final Map<String,Object> properties=KoalaUtils.projectProperties(project,this.client.loggedInUser());
  this.client.track(KoalaEvent.VIEWED_PROJECT_DASHBOARD,properties);
}
