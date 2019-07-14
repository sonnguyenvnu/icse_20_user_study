protected void refreshAccessibilityDelegatesIfNeeded(boolean isAccessibilityEnabled){
  if (isAccessibilityEnabled == mIsComponentAccessibilityDelegateSet) {
    return;
  }
  if (isAccessibilityEnabled && mComponentAccessibilityDelegate == null) {
    mComponentAccessibilityDelegate=new ComponentAccessibilityDelegate(this,this.isFocusable(),ViewCompat.getImportantForAccessibility(this));
  }
  ViewCompat.setAccessibilityDelegate(this,isAccessibilityEnabled ? mComponentAccessibilityDelegate : null);
  mIsComponentAccessibilityDelegateSet=isAccessibilityEnabled;
  if (!isAccessibilityEnabled) {
    return;
  }
  for (int i=0, size=getChildCount(); i < size; i++) {
    final View child=getChildAt(i);
    if (child instanceof ComponentHost) {
      ((ComponentHost)child).refreshAccessibilityDelegatesIfNeeded(true);
    }
 else {
      final NodeInfo nodeInfo=(NodeInfo)child.getTag(R.id.component_node_info);
      if (nodeInfo != null) {
        ViewCompat.setAccessibilityDelegate(child,new ComponentAccessibilityDelegate(child,nodeInfo,child.isFocusable(),ViewCompat.getImportantForAccessibility(child)));
      }
    }
  }
}
