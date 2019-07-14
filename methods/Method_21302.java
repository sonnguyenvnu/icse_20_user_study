@Override public void bindData(final @Nullable Object data) throws Exception {
  final Pair<Project,List<ProjectStatsEnvelope.ReferrerStats>> projectAndReferrerStats=requireNonNull((Pair<Project,List<ProjectStatsEnvelope.ReferrerStats>>)data);
  this.viewModel.inputs.projectAndReferrerStatsInput(projectAndReferrerStats);
}
