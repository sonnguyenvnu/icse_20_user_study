/** 
 * Returns time until project reaches deadline in seconds, or 0 if the project has already finished.
 */
public static @NonNull Long timeInSecondsUntilDeadline(final @NonNull Project project){
  return Math.max(0L,new Duration(new DateTime(),project.deadline()).getStandardSeconds());
}
