public void resumeDelayedFragmentAnimation(){
  if (delayedOpenAnimationRunnable == null) {
    return;
  }
  AndroidUtilities.cancelRunOnUIThread(delayedOpenAnimationRunnable);
  delayedOpenAnimationRunnable.run();
  delayedOpenAnimationRunnable=null;
}
