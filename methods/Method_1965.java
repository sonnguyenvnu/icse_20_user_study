@VisibleForTesting public void setAllowAnimations(boolean allowAnimations){
  mAllowAnimations=allowAnimations;
  supportInvalidateOptionsMenu();
  updateAdapter(null);
  loadUrls();
}
