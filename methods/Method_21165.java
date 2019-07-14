@OnTextChanged(R.id.password) void onPasswordTextChange(final @NonNull CharSequence password){
  this.viewModel.inputs.password(password.toString());
}
