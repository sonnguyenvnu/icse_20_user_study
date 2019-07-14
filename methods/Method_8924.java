public int getSelectionLength(){
  if (messageEditText == null) {
    return 0;
  }
  try {
    return messageEditText.getSelectionEnd() - messageEditText.getSelectionStart();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return 0;
}
