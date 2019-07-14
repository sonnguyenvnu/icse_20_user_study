/** 
 * Returns time until project reaches deadline along with the unit, e.g. `25 minutes`, `8 days`.
 */
public static @NonNull String deadlineCountdown(final @NonNull Project project,final @NonNull Context context){
  return new StringBuilder().append(deadlineCountdownValue(project)).append(" ").append(deadlineCountdownUnit(project,context)).toString();
}
