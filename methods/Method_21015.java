@Override public @NonNull Observable<Project> fetchProject(final @NonNull Project project){
  return fetchProject(project.param()).startWith(project);
}
