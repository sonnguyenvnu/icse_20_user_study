public static boolean isAvailable(final @NonNull Project project,final @NonNull Reward reward){
  return project.isLive() && !RewardUtils.isLimitReached(reward) && !RewardUtils.isExpired(reward);
}
