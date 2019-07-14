@OnTextChanged(R.id.name) void onNameTextChanged(final @NonNull CharSequence name){
  this.viewModel.inputs.name(name.toString());
}
