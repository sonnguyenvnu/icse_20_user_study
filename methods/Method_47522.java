public void invalidateToolbar(){
  if (rootView == null)   return;
  activity.runOnUiThread(() -> {
    Toolbar toolbar=rootView.getToolbar();
    activity.setSupportActionBar(toolbar);
    ActionBar actionBar=activity.getSupportActionBar();
    if (actionBar == null)     return;
    actionBar.setDisplayHomeAsUpEnabled(rootView.getDisplayHomeAsUp());
    int color=rootView.getToolbarColor();
    setActionBarColor(actionBar,color);
    setStatusBarColor(color);
  }
);
}
