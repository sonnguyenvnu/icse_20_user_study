private void setMailboxStrings(final @NonNull Integer stringRes){
  final String mailbox=getString(stringRes);
  this.mailboxTextView.setText(mailbox);
  this.collapsedToolbarMailboxTitle.setText(mailbox);
  this.switchMailboxButton.setText(mailbox.equals(this.inboxString) ? this.sentString : this.inboxString);
}
