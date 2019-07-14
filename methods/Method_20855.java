/** 
 * Returns `true` if the reward has a valid expiration date.
 */
public static boolean isTimeLimited(final @NonNull Reward reward){
  return reward.endsAt() != null && !DateTimeUtils.isEpoch(reward.endsAt());
}
