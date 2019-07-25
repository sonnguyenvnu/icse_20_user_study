private void register(UsageViewData viewData){
  if (myUsageViewsData.isEmpty()) {
    new RepoListenerRegistrar(ProjectHelper.getProjectRepository(getProject()),myChangeTracker).attach();
  }
  myUsageViewsData.add(viewData);
}
