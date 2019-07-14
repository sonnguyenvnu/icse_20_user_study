/** 
 * Returns `true` if the reward has items, `false` otherwise.
 */
public static boolean isItemized(final @NonNull Reward reward){
  final List<RewardsItem> rewardsItems=reward.rewardsItems();
  return rewardsItems != null && !rewardsItems.isEmpty();
}
