public void showLastFragment(){
  if (fragmentsStack.isEmpty()) {
    return;
  }
  for (int a=0; a < fragmentsStack.size() - 1; a++) {
    BaseFragment previousFragment=fragmentsStack.get(a);
    if (previousFragment.actionBar != null && previousFragment.actionBar.getAddToContainer()) {
      ViewGroup parent=(ViewGroup)previousFragment.actionBar.getParent();
      if (parent != null) {
        parent.removeView(previousFragment.actionBar);
      }
    }
    if (previousFragment.fragmentView != null) {
      ViewGroup parent=(ViewGroup)previousFragment.fragmentView.getParent();
      if (parent != null) {
        previousFragment.onPause();
        previousFragment.onRemoveFromParent();
        parent.removeView(previousFragment.fragmentView);
      }
    }
  }
  BaseFragment previousFragment=fragmentsStack.get(fragmentsStack.size() - 1);
  previousFragment.setParentLayout(this);
  View fragmentView=previousFragment.fragmentView;
  if (fragmentView == null) {
    fragmentView=previousFragment.createView(parentActivity);
  }
 else {
    ViewGroup parent=(ViewGroup)fragmentView.getParent();
    if (parent != null) {
      previousFragment.onRemoveFromParent();
      parent.removeView(fragmentView);
    }
  }
  if (previousFragment.actionBar != null && previousFragment.actionBar.getAddToContainer()) {
    if (removeActionBarExtraHeight) {
      previousFragment.actionBar.setOccupyStatusBar(false);
    }
    ViewGroup parent=(ViewGroup)previousFragment.actionBar.getParent();
    if (parent != null) {
      parent.removeView(previousFragment.actionBar);
    }
    containerView.addView(previousFragment.actionBar);
    previousFragment.actionBar.setTitleOverlayText(titleOverlayText,titleOverlayTextId,overlayAction);
  }
  containerView.addView(fragmentView,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  previousFragment.onResume();
  currentActionBar=previousFragment.actionBar;
  if (!previousFragment.hasOwnBackground && fragmentView.getBackground() == null) {
    fragmentView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
  }
}
