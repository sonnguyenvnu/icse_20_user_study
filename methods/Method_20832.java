/** 
 * Returns time remaining until project reaches deadline in either seconds, minutes, hours or days. A time unit is chosen such that the number is readable, e.g. 5 minutes would be preferred to 300 seconds.
 * @return the Integer time remaining.
 */
public static int deadlineCountdownValue(final @NonNull Project project){
  final Long seconds=timeInSecondsUntilDeadline(project);
  if (seconds <= 120.0) {
    return seconds.intValue();
  }
 else   if (seconds <= 120.0 * 60.0) {
    return (int)Math.floor(seconds / 60.0);
  }
 else   if (seconds < 72.0 * 60.0 * 60.0) {
    return (int)Math.floor(seconds / 60.0 / 60.0);
  }
  return (int)Math.floor(seconds / 60.0 / 60.0 / 24.0);
}
