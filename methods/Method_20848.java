/** 
 * Returns `true` if the reward has backers, `false` otherwise.
 */
public static boolean hasBackers(final @NonNull Reward reward){
  return IntegerUtils.isNonZero(reward.backersCount());
}
