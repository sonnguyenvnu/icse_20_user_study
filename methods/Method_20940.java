public static @NonNull Reward noReward(){
  return Reward.builder().id(0).estimatedDeliveryOn(null).description("No reward").minimum(1.0f).build();
}
