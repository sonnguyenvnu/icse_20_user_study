@Override public @NonNull Observable<DiscoverEnvelope> fetchProjects(final @NonNull DiscoveryParams params){
  return Observable.just(DiscoverEnvelope.builder().projects(Arrays.asList(ProjectFactory.project(),ProjectFactory.allTheWayProject(),ProjectFactory.successfulProject())).urls(DiscoverEnvelope.UrlsEnvelope.builder().api(DiscoverEnvelope.UrlsEnvelope.ApiEnvelope.builder().moreProjects("http://more.projects.please").build()).build()).build());
}
