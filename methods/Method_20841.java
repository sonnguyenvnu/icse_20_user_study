/** 
 * Name of the cookie that should store the ref tag for a particular project. Fits the template: ref_{project_pid}
 */
protected static @NonNull String cookieNameForProject(final @NonNull Project project){
  return "ref_" + project.id();
}
