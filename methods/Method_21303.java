@Override public void bindData(final @Nullable Object data) throws Exception {
  final Pair<Project,ProjectStatsEnvelope.RewardStats> projectAndRewardStats=requireNonNull((Pair<Project,ProjectStatsEnvelope.RewardStats>)data);
  this.viewModel.inputs.projectAndRewardStats(projectAndRewardStats);
}
