@Override public void bindData(final @Nullable Object data) throws Exception {
  final Pair<Project,List<ProjectStatsEnvelope.RewardStats>> projectAndRewardStats=requireNonNull((Pair<Project,List<ProjectStatsEnvelope.RewardStats>>)data);
  this.viewModel.inputs.projectAndRewardStatsInput(projectAndRewardStats);
}
