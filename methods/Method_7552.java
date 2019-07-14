public void showActionModeTop(){
  if (occupyStatusBar && actionModeTop == null) {
    actionModeTop=new View(getContext());
    actionModeTop.setBackgroundColor(Theme.getColor(Theme.key_actionBarActionModeDefaultTop));
    addView(actionModeTop);
    FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)actionModeTop.getLayoutParams();
    layoutParams.height=AndroidUtilities.statusBarHeight;
    layoutParams.width=LayoutHelper.MATCH_PARENT;
    layoutParams.gravity=Gravity.TOP | Gravity.LEFT;
    actionModeTop.setLayoutParams(layoutParams);
  }
}
