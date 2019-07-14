/** 
 * Returns `true` if the project is no longer live, `false` otherwise.
 */
public static boolean isCompleted(final @NonNull Project project){
  final String state=project.state();
  return Project.STATE_CANCELED.equals(state) || Project.STATE_FAILED.equals(state) || Project.STATE_SUCCESSFUL.equals(state) || Project.STATE_PURGED.equals(state) || Project.STATE_SUSPENDED.equals(state);
}
