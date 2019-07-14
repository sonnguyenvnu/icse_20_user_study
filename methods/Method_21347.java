@Override public void bindData(final @Nullable Object data) throws Exception {
  this.user=requireNonNull((User)data,User.class);
}
