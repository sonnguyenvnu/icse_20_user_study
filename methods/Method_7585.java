public void onActionModeStarted(Object mode){
  if (currentActionBar != null) {
    currentActionBar.setVisibility(GONE);
  }
  inActionMode=true;
}
