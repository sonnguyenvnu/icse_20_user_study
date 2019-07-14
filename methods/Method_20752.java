public static boolean isBacked(final @NonNull Project project,final @NonNull Reward reward){
  final Backing backing=project.backing();
  if (backing == null) {
    return false;
  }
  final Long rewardId=backing.rewardId();
  if (rewardId == null) {
    return RewardUtils.isNoReward(reward);
  }
  return rewardId == reward.id();
}
