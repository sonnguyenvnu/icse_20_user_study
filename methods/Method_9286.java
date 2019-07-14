private void checkLayout(){
  if (!AndroidUtilities.isTablet() || rightActionBarLayout == null) {
    return;
  }
  if (!AndroidUtilities.isInMultiwindow && (!AndroidUtilities.isSmallTablet() || getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)) {
    tabletFullSize=false;
    if (actionBarLayout.fragmentsStack.size() >= 2) {
      for (int a=1; a < actionBarLayout.fragmentsStack.size(); a++) {
        BaseFragment chatFragment=actionBarLayout.fragmentsStack.get(a);
        if (chatFragment instanceof ChatActivity) {
          ((ChatActivity)chatFragment).setIgnoreAttachOnPause(true);
        }
        chatFragment.onPause();
        actionBarLayout.fragmentsStack.remove(a);
        rightActionBarLayout.fragmentsStack.add(chatFragment);
        a--;
      }
      if (passcodeView.getVisibility() != View.VISIBLE) {
        actionBarLayout.showLastFragment();
        rightActionBarLayout.showLastFragment();
      }
    }
    rightActionBarLayout.setVisibility(rightActionBarLayout.fragmentsStack.isEmpty() ? View.GONE : View.VISIBLE);
    backgroundTablet.setVisibility(rightActionBarLayout.fragmentsStack.isEmpty() ? View.VISIBLE : View.GONE);
    shadowTabletSide.setVisibility(!actionBarLayout.fragmentsStack.isEmpty() ? View.VISIBLE : View.GONE);
  }
 else {
    tabletFullSize=true;
    if (!rightActionBarLayout.fragmentsStack.isEmpty()) {
      for (int a=0; a < rightActionBarLayout.fragmentsStack.size(); a++) {
        BaseFragment chatFragment=rightActionBarLayout.fragmentsStack.get(a);
        if (chatFragment instanceof ChatActivity) {
          ((ChatActivity)chatFragment).setIgnoreAttachOnPause(true);
        }
        chatFragment.onPause();
        rightActionBarLayout.fragmentsStack.remove(a);
        actionBarLayout.fragmentsStack.add(chatFragment);
        a--;
      }
      if (passcodeView.getVisibility() != View.VISIBLE) {
        actionBarLayout.showLastFragment();
      }
    }
    shadowTabletSide.setVisibility(View.GONE);
    rightActionBarLayout.setVisibility(View.GONE);
    backgroundTablet.setVisibility(!actionBarLayout.fragmentsStack.isEmpty() ? View.GONE : View.VISIBLE);
  }
}
