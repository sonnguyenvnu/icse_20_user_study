private void hideActionMode(){
  if (!actionBar.isActionModeShowed()) {
    return;
  }
  if (actionBar != null) {
    actionBar.hideActionMode();
  }
  cantDeleteMessagesCount=0;
  canEditMessagesCount=0;
  cantForwardMessagesCount=0;
  if (chatActivityEnterView != null) {
    EditTextCaption editTextCaption=chatActivityEnterView.getEditField();
    editTextCaption.requestFocus();
    editTextCaption.setAllowDrawCursor(true);
  }
}
