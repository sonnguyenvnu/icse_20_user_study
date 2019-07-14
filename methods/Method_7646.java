protected void onBecomeFullyVisible(){
  AccessibilityManager mgr=(AccessibilityManager)ApplicationLoader.applicationContext.getSystemService(Context.ACCESSIBILITY_SERVICE);
  if (mgr.isEnabled()) {
    ActionBar actionBar=getActionBar();
    if (actionBar != null) {
      String title=actionBar.getTitle();
      if (!TextUtils.isEmpty(title)) {
        setParentActivityTitle(title);
      }
    }
  }
}
