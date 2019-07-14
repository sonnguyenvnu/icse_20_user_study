public static boolean isShippable(final @NonNull Backing backing){
  final Reward reward=backing.reward();
  if (reward == null) {
    return false;
  }
  return isTrue(reward.shippingEnabled());
}
