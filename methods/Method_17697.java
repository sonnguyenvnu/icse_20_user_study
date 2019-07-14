public static boolean isRunningApplicableAccessibilityService(AccessibilityManager manager){
  if (manager == null || !manager.isEnabled()) {
    return false;
  }
  return manager.isTouchExplorationEnabled() || enabledServiceCanFocusAndRetrieveWindowContent(manager);
}
