@Override public void bindData(final @Nullable Object data) throws Exception {
  final Pair<Project,ProjectStatsEnvelope.ReferrerStats> projectAndReferrerStats=requireNonNull((Pair<Project,ProjectStatsEnvelope.ReferrerStats>)data);
  this.viewModel.inputs.projectAndReferrerStatsInput(projectAndReferrerStats);
}
