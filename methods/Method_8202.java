@Override public boolean onBackPressed(){
  if (nameTextView != null && nameTextView.isPopupShowing()) {
    nameTextView.hidePopup(true);
    return false;
  }
  return true;
}
