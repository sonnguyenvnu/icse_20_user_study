@Override public void bindData(final @Nullable Object data) throws Exception {
  this.activity=ObjectUtils.requireNonNull((Activity)data,Activity.class);
}
