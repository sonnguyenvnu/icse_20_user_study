boolean isAccessible(){
  if (mComponent == null) {
    return false;
  }
  if (mImportantForAccessibility == IMPORTANT_FOR_ACCESSIBILITY_NO) {
    return false;
  }
  return (mNodeInfo != null && mNodeInfo.needsAccessibilityDelegate()) || mComponent.implementsAccessibility();
}
