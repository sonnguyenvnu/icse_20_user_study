@Override public void bindData(final @Nullable Object data) throws Exception {
  final Pair<Project,ProjectStatsEnvelope> projectAndStats=requireNonNull((Pair<Project,ProjectStatsEnvelope>)data);
  this.viewModel.inputs.projectAndStatsInput(projectAndStats);
}
