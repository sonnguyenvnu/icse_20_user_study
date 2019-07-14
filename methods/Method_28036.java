@Override public void onHandleIntent(@Nullable Bundle intent){
  if (intent == null)   return;
  isRepo=intent.getBoolean(BundleConstant.EXTRA);
  url=intent.getString(BundleConstant.ITEM);
  htmlUrl=intent.getString(BundleConstant.EXTRA_TWO);
  if (!InputHelper.isEmpty(url)) {
    if (MarkDownProvider.isArchive(url)) {
      sendToView(view -> view.onShowError(R.string.archive_file_detected_error));
      return;
    }
    if (isRepo) {
      url=url.endsWith("/") ? (url + "readme") : (url + "/readme");
    }
    onWorkOnline();
  }
}
