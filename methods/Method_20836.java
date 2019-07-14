/** 
 * Returns time between project launch and deadline.
 */
public static @NonNull Long timeInSecondsOfDuration(final @NonNull Project project){
  return new Duration(project.launchedAt(),project.deadline()).getStandardSeconds();
}
