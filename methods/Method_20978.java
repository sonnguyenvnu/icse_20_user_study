@Override public @NonNull Observable<Project> saveProject(final @NonNull Project project){
  return Observable.just(project.toBuilder().isStarred(true).build());
}
