private void setEditingEnabled(boolean enabled){
  mTitleField.setEnabled(enabled);
  mBodyField.setEnabled(enabled);
  if (enabled) {
    mSubmitButton.show();
  }
 else {
    mSubmitButton.hide();
  }
}
