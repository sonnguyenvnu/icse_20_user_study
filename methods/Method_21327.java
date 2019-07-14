@Override public void bindData(final @Nullable Object data) throws Exception {
  final MessageThread messageThread=requireNonNull((MessageThread)data);
  this.viewModel.inputs.configureWith(messageThread);
}
