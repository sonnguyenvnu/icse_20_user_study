@Override void updateLabelState(boolean animate){
  EditText realEditText=editText;
  editText=mHasTextEditText;
  super.updateLabelState(animate);
  editText=realEditText;
}
