/** 
 * Returns the most appropriate unit for the time remaining until the reward reaches its deadline.
 * @param context an Android context.
 * @return         the String unit.
 */
public static @NonNull String deadlineCountdownUnit(final @NonNull Reward reward,final @NonNull Context context){
  final Long seconds=timeInSecondsUntilDeadline(reward);
  if (seconds <= 1.0 && seconds > 0.0) {
    return context.getString(R.string.discovery_baseball_card_deadline_units_secs);
  }
 else   if (seconds <= 120.0) {
    return context.getString(R.string.discovery_baseball_card_deadline_units_secs);
  }
 else   if (seconds <= 120.0 * 60.0) {
    return context.getString(R.string.discovery_baseball_card_deadline_units_mins);
  }
 else   if (seconds <= 72.0 * 60.0 * 60.0) {
    return context.getString(R.string.discovery_baseball_card_deadline_units_hours);
  }
  return context.getString(R.string.discovery_baseball_card_deadline_units_days);
}
