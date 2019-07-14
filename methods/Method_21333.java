@Override public void bindData(final @Nullable Object data) throws Exception {
  final Pair<Project,DiscoveryParams> projectAndParams=ObjectUtils.requireNonNull((Pair<Project,DiscoveryParams>)data);
  this.viewModel.inputs.configureWith(projectAndParams);
}
