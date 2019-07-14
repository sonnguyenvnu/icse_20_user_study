@Override public void onRebuildAllFragments(ActionBarLayout layout,boolean last){
  if (AndroidUtilities.isTablet()) {
    if (layout == layersActionBarLayout) {
      rightActionBarLayout.rebuildAllFragmentViews(last,last);
      actionBarLayout.rebuildAllFragmentViews(last,last);
    }
  }
  drawerLayoutAdapter.notifyDataSetChanged();
}
