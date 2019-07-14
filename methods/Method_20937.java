public static @NonNull Reward maxReward(final @NonNull Country country){
  return reward().toBuilder().minimum(country.getMaxPledge()).backersCount(0).build();
}
