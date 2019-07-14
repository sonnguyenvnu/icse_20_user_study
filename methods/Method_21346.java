@Override public void bindData(final @Nullable Object data) throws Exception {
  @SuppressWarnings("unchecked") final Pair<Project,Boolean> projectAndIsFeatured=ObjectUtils.requireNonNull((Pair<Project,Boolean>)data);
  this.viewModel.inputs.configureWith(projectAndIsFeatured);
}
