/** 
 * Returns `true` if the reward has a limit set, and the limit has not been reached, `false` otherwise.
 */
public static boolean isLimited(final @NonNull Reward reward){
  return reward.limit() != null && !isLimitReached(reward);
}
