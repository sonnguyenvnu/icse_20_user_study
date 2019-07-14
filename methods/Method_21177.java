@OnTextChanged(R.id.code) public void codeEditTextOnTextChanged(final @NonNull CharSequence code){
  this.viewModel.inputs.code(code.toString());
}
