public void endEditing(){
  editText.clearFocus();
  editText.setEnabled(false);
  editText.setClickable(false);
  updateSelectionView();
}
