/** 
 * Returns time until reward reaches deadline in seconds, or 0 if the reward has already finished.
 */
public static @NonNull Long timeInSecondsUntilDeadline(final @NonNull Reward reward){
  return Math.max(0L,new Duration(new DateTime(),reward.endsAt()).getStandardSeconds());
}
