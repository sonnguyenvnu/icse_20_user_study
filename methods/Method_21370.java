@Override @SuppressWarnings("unchecked") public void bindData(final @Nullable Object data) throws Exception {
  final Pair<Project,Reward> projectAndReward=requireNonNull((Pair<Project,Reward>)data);
  final Project project=requireNonNull(projectAndReward.first,Project.class);
  final Reward reward=requireNonNull(projectAndReward.second,Reward.class);
  this.viewModel.inputs.projectAndReward(project,reward);
}
