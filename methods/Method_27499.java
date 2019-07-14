@Override public void onOpenUrlInBrowser(){
  if (!InputHelper.isEmpty(schemeUrl)) {
    ActivityHelper.startCustomTab(this,schemeUrl);
    try {
      finish();
    }
 catch (    Exception ignored) {
    }
  }
}
