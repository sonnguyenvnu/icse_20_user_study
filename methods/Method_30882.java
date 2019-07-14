private void setupToolbar(){
  if (Settings.CREATE_NEW_TASK_FOR_WEBVIEW.getValue()) {
    mToolbar.setNavigationIcon(R.drawable.close_icon_white_24dp);
  }
  setSupportActionBar(mToolbar);
  updateToolbarTitleAndSubtitle();
}
