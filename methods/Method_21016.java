@Override public @NonNull Observable<DiscoverEnvelope> fetchProjects(final @NonNull DiscoveryParams params){
  return this.service.projects(params.queryParams()).lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
