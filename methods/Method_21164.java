@OnTextChanged(R.id.email) void onEmailTextChanged(final @NonNull CharSequence email){
  this.viewModel.inputs.email(email.toString());
}
