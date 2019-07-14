@Override public @NonNull Observable<Project> toggleProjectSave(final @NonNull Project project){
  return Observable.just(project.toBuilder().isStarred(!project.isStarred()).build());
}
