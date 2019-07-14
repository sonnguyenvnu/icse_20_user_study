/** 
 * Callback used to notify about top-level-drawable's visibility changes.
 */
@Override public void onVisibilityChange(boolean isVisible){
  if (mIsVisible == isVisible) {
    return;
  }
  mEventTracker.recordEvent(isVisible ? Event.ON_DRAWABLE_SHOW : Event.ON_DRAWABLE_HIDE);
  mIsVisible=isVisible;
  attachOrDetachController();
}
