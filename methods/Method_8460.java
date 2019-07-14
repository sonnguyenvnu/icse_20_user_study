public void setFieldFocused(){
  AccessibilityManager am=(AccessibilityManager)parentActivity.getSystemService(Context.ACCESSIBILITY_SERVICE);
  if (messageEditText != null && !am.isTouchExplorationEnabled()) {
    try {
      messageEditText.requestFocus();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
}
