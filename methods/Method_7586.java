public void onActionModeFinished(Object mode){
  if (currentActionBar != null) {
    currentActionBar.setVisibility(VISIBLE);
  }
  inActionMode=false;
}
