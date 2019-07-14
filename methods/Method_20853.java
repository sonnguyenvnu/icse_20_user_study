/** 
 * Returns `true` if the reward's limit has been reached, `false` otherwise.
 */
public static boolean isLimitReached(final @NonNull Reward reward){
  final Integer remaining=reward.remaining();
  return reward.limit() != null && remaining != null && remaining <= 0;
}
