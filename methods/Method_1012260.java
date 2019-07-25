/** 
 * Shows GuideCaseView
 */
public void show(){
  if (mActivity == null || (mId != null && isShownBefore())) {
    if (mDismissListener != null) {
      mDismissListener.onSkipped(mId);
    }
    return;
  }
  if (mView != null) {
    if (mView.getWidth() == 0 && mView.getHeight() == 0) {
      mView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }
 else {
      focus();
    }
  }
 else {
    focus();
  }
}
