public ActionBarMenu createActionMode(boolean needTop){
  if (actionMode != null) {
    return actionMode;
  }
  actionMode=new ActionBarMenu(getContext(),this);
  actionMode.isActionMode=true;
  actionMode.setBackgroundColor(Theme.getColor(Theme.key_actionBarActionModeDefault));
  addView(actionMode,indexOfChild(backButtonImageView));
  actionMode.setPadding(0,occupyStatusBar ? AndroidUtilities.statusBarHeight : 0,0,0);
  FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)actionMode.getLayoutParams();
  layoutParams.height=LayoutHelper.MATCH_PARENT;
  layoutParams.width=LayoutHelper.MATCH_PARENT;
  layoutParams.bottomMargin=extraHeight;
  layoutParams.gravity=Gravity.RIGHT;
  actionMode.setLayoutParams(layoutParams);
  actionMode.setVisibility(INVISIBLE);
  if (occupyStatusBar && needTop && actionModeTop == null) {
    actionModeTop=new View(getContext());
    actionModeTop.setBackgroundColor(Theme.getColor(Theme.key_actionBarActionModeDefaultTop));
    addView(actionModeTop);
    layoutParams=(FrameLayout.LayoutParams)actionModeTop.getLayoutParams();
    layoutParams.height=AndroidUtilities.statusBarHeight;
    layoutParams.width=LayoutHelper.MATCH_PARENT;
    layoutParams.gravity=Gravity.TOP | Gravity.LEFT;
    actionModeTop.setLayoutParams(layoutParams);
    actionModeTop.setVisibility(INVISIBLE);
  }
  return actionMode;
}
