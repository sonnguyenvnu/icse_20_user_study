@Override public void bindData(final @Nullable Object data) throws Exception {
  final Message message=ObjectUtils.requireNonNull((Message)data);
  this.viewModel.inputs.configureWith(message);
}
