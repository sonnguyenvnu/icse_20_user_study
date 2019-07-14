public void needLayout(){
  if (AndroidUtilities.isTablet()) {
    RelativeLayout.LayoutParams relativeLayoutParams=(RelativeLayout.LayoutParams)layersActionBarLayout.getLayoutParams();
    relativeLayoutParams.leftMargin=(AndroidUtilities.displaySize.x - relativeLayoutParams.width) / 2;
    int y=(Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0);
    relativeLayoutParams.topMargin=y + (AndroidUtilities.displaySize.y - relativeLayoutParams.height - y) / 2;
    layersActionBarLayout.setLayoutParams(relativeLayoutParams);
    if (!AndroidUtilities.isSmallTablet() || getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
      int leftWidth=AndroidUtilities.displaySize.x / 100 * 35;
      if (leftWidth < AndroidUtilities.dp(320)) {
        leftWidth=AndroidUtilities.dp(320);
      }
      relativeLayoutParams=(RelativeLayout.LayoutParams)actionBarLayout.getLayoutParams();
      relativeLayoutParams.width=leftWidth;
      relativeLayoutParams.height=LayoutHelper.MATCH_PARENT;
      actionBarLayout.setLayoutParams(relativeLayoutParams);
      if (AndroidUtilities.isSmallTablet() && actionBarLayout.fragmentsStack.size() == 2) {
        BaseFragment chatFragment=actionBarLayout.fragmentsStack.get(1);
        chatFragment.onPause();
        actionBarLayout.fragmentsStack.remove(1);
        actionBarLayout.showLastFragment();
      }
    }
 else {
      relativeLayoutParams=(RelativeLayout.LayoutParams)actionBarLayout.getLayoutParams();
      relativeLayoutParams.width=LayoutHelper.MATCH_PARENT;
      relativeLayoutParams.height=LayoutHelper.MATCH_PARENT;
      actionBarLayout.setLayoutParams(relativeLayoutParams);
    }
  }
}
