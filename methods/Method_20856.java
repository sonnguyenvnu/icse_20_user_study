/** 
 * Returns unit of time remaining in a readable string, e.g. `days to go`, `hours to go`.
 */
public static @NonNull String deadlineCountdownDetail(final @NonNull Reward reward,final @NonNull Context context,final @NonNull KSString ksString){
  return ksString.format(context.getString(R.string.discovery_baseball_card_time_left_to_go),"time_left",deadlineCountdownUnit(reward,context));
}
