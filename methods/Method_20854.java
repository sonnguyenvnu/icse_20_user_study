/** 
 * Returns `true` if the reward has shipping enabled, `false` otherwise.
 */
public static boolean isShippable(final @NonNull Reward reward){
  return isTrue(reward.shippingEnabled());
}
