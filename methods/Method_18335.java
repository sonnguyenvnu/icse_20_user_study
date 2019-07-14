public void rerenderForAccessibility(boolean enabled){
  refreshAccessibilityDelegatesIfNeeded(enabled);
  forceRelayout();
}
