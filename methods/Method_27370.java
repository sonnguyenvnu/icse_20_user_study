private void openNotification(long id,@Nullable String url,boolean readOnly){
  if (id > 0 && url != null) {
    AppHelper.cancelNotification(this,InputHelper.getSafeIntId(id));
    if (readOnly) {
      markSingleAsRead(id);
    }
 else     if (!PrefGetter.isMarkAsReadEnabled()) {
      markSingleAsRead(id);
    }
    if (!readOnly) {
      SchemeParser.launchUri(getApplicationContext(),Uri.parse(url),true,true);
    }
  }
}
