public void rebuildAllFragments(boolean last){
  if (layersActionBarLayout != null) {
    layersActionBarLayout.rebuildAllFragmentViews(last,last);
  }
 else {
    actionBarLayout.rebuildAllFragmentViews(last,last);
  }
}
