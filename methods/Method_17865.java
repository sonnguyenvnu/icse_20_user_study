/** 
 * Host views implement their own content description handling instead of just delegating to the underlying view framework for performance reasons as the framework sets/resets content description very frequently on host views and the underlying accessibility notifications might cause performance issues. This is safe to do because the framework owns the accessibility state and knows how to update it efficiently.
 */
@Override public void setContentDescription(CharSequence contentDescription){
  mContentDescription=contentDescription;
  if (!TextUtils.isEmpty(contentDescription) && ViewCompat.getImportantForAccessibility(this) == ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_AUTO) {
    ViewCompat.setImportantForAccessibility(this,ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_YES);
  }
  invalidateAccessibilityState();
}
