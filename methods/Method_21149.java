@OnClick(R.id.switch_mailbox_button) protected void mailboxSwitchClicked(){
  this.viewModel.inputs.mailbox(this.mailboxTextView.getText().equals(this.inboxString) ? Mailbox.SENT : Mailbox.INBOX);
}
