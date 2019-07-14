/** 
 * Returns `true` if the reward has expired.
 */
public static boolean isExpired(final @NonNull Reward reward){
  return isTimeLimited(reward) && reward.endsAt().isBeforeNow();
}
