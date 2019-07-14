@Override public boolean onBackPressed(){
  if (editText != null && editText.isPopupShowing()) {
    editText.hidePopup(true);
    return false;
  }
  return true;
}
