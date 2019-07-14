private void toggleMiniProgress(final boolean show,final boolean animated){
  if (animated) {
    toggleMiniProgressInternal(show);
    if (show) {
      if (miniProgressAnimator != null) {
        miniProgressAnimator.cancel();
        miniProgressAnimator=null;
      }
      AndroidUtilities.cancelRunOnUIThread(miniProgressShowRunnable);
      if (firstAnimationDelay) {
        firstAnimationDelay=false;
        toggleMiniProgressInternal(true);
      }
 else {
        AndroidUtilities.runOnUIThread(miniProgressShowRunnable,500);
      }
    }
 else {
      AndroidUtilities.cancelRunOnUIThread(miniProgressShowRunnable);
      if (miniProgressAnimator != null) {
        miniProgressAnimator.cancel();
        toggleMiniProgressInternal(false);
      }
    }
  }
 else {
    if (miniProgressAnimator != null) {
      miniProgressAnimator.cancel();
      miniProgressAnimator=null;
    }
    miniProgressView.setAlpha(show ? 1.0f : 0.0f);
    miniProgressView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
  }
}
