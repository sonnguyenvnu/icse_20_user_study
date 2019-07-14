private void setUnreadTextViewText(final @NonNull Integer unreadCount){
  final String unreadCountString=NumberUtils.format(unreadCount);
  this.unreadCountTextView.setText(this.ksString.format(this.unreadCountUnreadString,"unread_count",unreadCountString));
  this.unreadCountToolbarTextView.setText(StringUtils.wrapInParentheses(unreadCountString));
}
