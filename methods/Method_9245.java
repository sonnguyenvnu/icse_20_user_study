@Override public void onRebuildAllFragments(ActionBarLayout layout,boolean last){
  if (AndroidUtilities.isTablet()) {
    if (layout == layersActionBarLayout) {
      actionBarLayout.rebuildAllFragmentViews(last,last);
    }
  }
}
