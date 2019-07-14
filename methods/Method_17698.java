public static boolean enabledServiceCanFocusAndRetrieveWindowContent(AccessibilityManager manager){
  List<AccessibilityServiceInfo> enabledServices=manager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK);
  if (enabledServices == null) {
    return false;
  }
  for (  AccessibilityServiceInfo serviceInfo : enabledServices) {
    int eventTypes=serviceInfo.eventTypes;
    if ((eventTypes & AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED) != AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED) {
      continue;
    }
    int capabilities=AccessibilityServiceInfoCompat.getCapabilities(serviceInfo);
    if ((capabilities & AccessibilityServiceInfo.CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT) == AccessibilityServiceInfo.CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT) {
      return true;
    }
  }
  return false;
}
