@Override public void bindData(final @Nullable Object data) throws Exception {
  final Category category=requireNonNull((Category)data,Category.class);
  this.viewModel.getInputs().configureWith(category);
}
