@OnTextChanged(R.id.message_edit_text) public void onMessageEditTextChanged(final @NonNull CharSequence message){
  this.viewModel.inputs.messageEditTextChanged(message.toString());
}
