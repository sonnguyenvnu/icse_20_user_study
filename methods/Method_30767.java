public void setEditText(EditText editText,int maxLength){
  mEditText=editText;
  mMaxLength=maxLength;
  mEditText.addTextChangedListener(new TextWatcher(){
    @Override public void beforeTextChanged(    CharSequence s,    int start,    int count,    int after){
    }
    @Override public void onTextChanged(    CharSequence s,    int start,    int before,    int count){
    }
    @Override public void afterTextChanged(    Editable s){
      updateText();
    }
  }
);
  updateText();
}
