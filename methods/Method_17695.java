private static synchronized void updateCachedIsAccessibilityEnabled(AccessibilityManager manager){
  cachedIsAccessibilityEnabled=Boolean.getBoolean("is_accessibility_enabled") || isRunningApplicableAccessibilityService(manager);
  isCachedIsAccessibilityEnabledSet=true;
}
