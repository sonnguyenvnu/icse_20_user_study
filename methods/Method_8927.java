public void setFieldFocused(boolean focus){
  if (messageEditText == null) {
    return;
  }
  if (focus) {
    if (!messageEditText.isFocused()) {
      messageEditText.postDelayed(() -> {
        if (messageEditText != null) {
          try {
            messageEditText.requestFocus();
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
      }
,600);
    }
  }
 else {
    if (messageEditText.isFocused() && !keyboardVisible) {
      messageEditText.clearFocus();
    }
  }
}
