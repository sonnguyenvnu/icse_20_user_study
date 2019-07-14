@Override public @NonNull Observable<Project> fetchProject(final @NonNull String param){
  return Observable.just(ProjectFactory.project().toBuilder().slug(param).build());
}
