public int getCursorPosition(){
  if (messageEditText == null) {
    return 0;
  }
  return messageEditText.getSelectionStart();
}
