boolean isCompatibleAccessibility(){
  return AccessibilityUtils.isAccessibilityEnabled(mAccessibilityManager) == mAccessibilityEnabled;
}
