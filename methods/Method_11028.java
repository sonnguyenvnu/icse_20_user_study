public void setColorEdit(EditText colorEdit){
  this.colorEdit=colorEdit;
  if (this.colorEdit != null) {
    this.colorEdit.setVisibility(View.VISIBLE);
    this.colorEdit.addTextChangedListener(colorTextChange);
    setColorEditTextColor(pickerTextColor);
  }
}
