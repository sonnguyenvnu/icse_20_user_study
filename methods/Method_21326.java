@Override public void bindData(final @Nullable Object data) throws Exception {
  final DateTime dateTime=ObjectUtils.requireNonNull((DateTime)data);
  this.centerTimestampTextView.setText(DateTimeUtils.longDate(dateTime));
}
