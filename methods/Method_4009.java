void dispatchPendingImportantForAccessibilityChanges(){
  for (int i=mPendingAccessibilityImportanceChange.size() - 1; i >= 0; i--) {
    ViewHolder viewHolder=mPendingAccessibilityImportanceChange.get(i);
    if (viewHolder.itemView.getParent() != this || viewHolder.shouldIgnore()) {
      continue;
    }
    int state=viewHolder.mPendingAccessibilityState;
    if (state != ViewHolder.PENDING_ACCESSIBILITY_STATE_NOT_SET) {
      ViewCompat.setImportantForAccessibility(viewHolder.itemView,state);
      viewHolder.mPendingAccessibilityState=ViewHolder.PENDING_ACCESSIBILITY_STATE_NOT_SET;
    }
  }
  mPendingAccessibilityImportanceChange.clear();
}
