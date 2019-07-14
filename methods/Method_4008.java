/** 
 * This method is here so that we can control the important for a11y changes and test it.
 */
@VisibleForTesting boolean setChildImportantForAccessibilityInternal(ViewHolder viewHolder,int importantForAccessibility){
  if (isComputingLayout()) {
    viewHolder.mPendingAccessibilityState=importantForAccessibility;
    mPendingAccessibilityImportanceChange.add(viewHolder);
    return false;
  }
  ViewCompat.setImportantForAccessibility(viewHolder.itemView,importantForAccessibility);
  return true;
}
