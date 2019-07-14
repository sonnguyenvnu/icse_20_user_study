private void onDrawerAnimationEnd(boolean opened){
  startedTracking=false;
  currentAnimation=null;
  drawerOpened=opened;
  if (!opened) {
    if (drawerLayout instanceof ListView) {
      ((ListView)drawerLayout).setSelectionFromTop(0,0);
    }
  }
  if (Build.VERSION.SDK_INT >= 19) {
    for (int i=0; i < getChildCount(); i++) {
      View child=getChildAt(i);
      if (child != drawerLayout) {
        child.setImportantForAccessibility(opened ? View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS : View.IMPORTANT_FOR_ACCESSIBILITY_AUTO);
      }
    }
  }
  sendAccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED);
}
