public static void makeAccessibilityAnnouncement(CharSequence what){
  AccessibilityManager am=(AccessibilityManager)ApplicationLoader.applicationContext.getSystemService(Context.ACCESSIBILITY_SERVICE);
  if (am.isEnabled()) {
    AccessibilityEvent ev=AccessibilityEvent.obtain();
    ev.setEventType(AccessibilityEvent.TYPE_ANNOUNCEMENT);
    ev.getText().add(what);
    am.sendAccessibilityEvent(ev);
  }
}
