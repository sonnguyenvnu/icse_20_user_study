@Override public void bindData(final @Nullable Object data) throws Exception {
  final Pair<Project,ProjectStatsEnvelope> projectAndProjectStats=requireNonNull((Pair<Project,ProjectStatsEnvelope>)data);
  this.viewModel.inputs.projectAndStats(projectAndProjectStats);
}
