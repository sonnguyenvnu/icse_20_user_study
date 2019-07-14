@Override public void bindData(final @Nullable Object data) throws Exception {
  final ProjectNotification projectNotification=requireNonNull((ProjectNotification)data,ProjectNotification.class);
  this.viewModel.projectNotification(projectNotification);
}
