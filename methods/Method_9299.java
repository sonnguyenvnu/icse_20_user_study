@Override public void onActionModeFinished(ActionMode mode){
  super.onActionModeFinished(mode);
  if (visibleActionMode == mode) {
    visibleActionMode=null;
  }
  if (Build.VERSION.SDK_INT >= 23 && mode.getType() == ActionMode.TYPE_FLOATING) {
    return;
  }
  actionBarLayout.onActionModeFinished(mode);
  if (AndroidUtilities.isTablet()) {
    rightActionBarLayout.onActionModeFinished(mode);
    layersActionBarLayout.onActionModeFinished(mode);
  }
}
