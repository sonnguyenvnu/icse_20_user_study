@Override public void setAccessibilityDelegate(AccessibilityDelegate delegate){
  if (!mLockAccessibilityDelegate) {
    super.setAccessibilityDelegate(delegate);
  }
}
