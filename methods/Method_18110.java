@Override public boolean hasVisibilityHandlers(){
  return mVisibleHandler != null || mFocusedHandler != null || mUnfocusedHandler != null || mFullImpressionHandler != null || mInvisibleHandler != null || mVisibilityChangedHandler != null;
}
