public static void hideTextInputLayoutErrorOnTextChange(@NonNull EditText editText,@NonNull TextInputLayout textInputLayout){
  editText.addTextChangedListener(new TextWatcher(){
    @Override public void beforeTextChanged(    @NonNull CharSequence s,    int start,    int count,    int after){
    }
    @Override public void onTextChanged(    @NonNull CharSequence s,    int start,    int before,    int count){
    }
    @Override public void afterTextChanged(    @NonNull Editable s){
      textInputLayout.setError(null);
    }
  }
);
}
