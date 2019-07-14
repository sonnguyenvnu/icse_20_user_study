private void onSlideAnimationEnd(final boolean backAnimation){
  if (!backAnimation) {
    if (fragmentsStack.size() < 2) {
      return;
    }
    BaseFragment lastFragment=fragmentsStack.get(fragmentsStack.size() - 1);
    lastFragment.onPause();
    lastFragment.onFragmentDestroy();
    lastFragment.setParentLayout(null);
    fragmentsStack.remove(fragmentsStack.size() - 1);
    LinearLayoutContainer temp=containerView;
    containerView=containerViewBack;
    containerViewBack=temp;
    bringChildToFront(containerView);
    lastFragment=fragmentsStack.get(fragmentsStack.size() - 1);
    currentActionBar=lastFragment.actionBar;
    lastFragment.onResume();
    lastFragment.onBecomeFullyVisible();
  }
 else {
    if (fragmentsStack.size() >= 2) {
      BaseFragment lastFragment=fragmentsStack.get(fragmentsStack.size() - 2);
      lastFragment.onPause();
      if (lastFragment.fragmentView != null) {
        ViewGroup parent=(ViewGroup)lastFragment.fragmentView.getParent();
        if (parent != null) {
          lastFragment.onRemoveFromParent();
          parent.removeView(lastFragment.fragmentView);
        }
      }
      if (lastFragment.actionBar != null && lastFragment.actionBar.getAddToContainer()) {
        ViewGroup parent=(ViewGroup)lastFragment.actionBar.getParent();
        if (parent != null) {
          parent.removeView(lastFragment.actionBar);
        }
      }
    }
  }
  containerViewBack.setVisibility(View.GONE);
  startedTracking=false;
  animationInProgress=false;
  containerView.setTranslationX(0);
  containerViewBack.setTranslationX(0);
  setInnerTranslationX(0);
}
