@Override public void bindData(final @Nullable Object data) throws Exception {
  final Project project=requireNonNull((Project)data);
  this.viewModel.inputs.configureWith(project);
}
