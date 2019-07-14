public void open(final boolean animate){
  if (!isOpened()) {
    if (isBackgroundEnabled()) {
      mShowBackgroundAnimator.start();
    }
    if (mIconAnimated) {
      if (mIconToggleSet != null) {
        mIconToggleSet.start();
      }
 else {
        mCloseAnimatorSet.cancel();
        mOpenAnimatorSet.start();
      }
    }
    int delay=0;
    int counter=0;
    mIsMenuOpening=true;
    for (int i=getChildCount() - 1; i >= 0; i--) {
      View child=getChildAt(i);
      if (child instanceof FloatingActionButton && child.getVisibility() != GONE) {
        counter++;
        final FloatingActionButton fab=(FloatingActionButton)child;
        mUiHandler.postDelayed(new Runnable(){
          @Override public void run(){
            if (isOpened())             return;
            if (fab != mMenuButton) {
              fab.show(animate);
            }
            Label label=(Label)fab.getTag(R.id.fab_label);
            if (label != null && label.isHandleVisibilityChanges()) {
              label.show(animate);
            }
          }
        }
,delay);
        delay+=mAnimationDelayPerItem;
      }
    }
    mUiHandler.postDelayed(new Runnable(){
      @Override public void run(){
        mMenuOpened=true;
        if (mToggleListener != null) {
          mToggleListener.onMenuToggle(true);
        }
      }
    }
,++counter * mAnimationDelayPerItem);
  }
}
