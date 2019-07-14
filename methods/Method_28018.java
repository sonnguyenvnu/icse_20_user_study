@Override public void onMessageDialogActionClicked(boolean isOk,@Nullable Bundle bundle){
  super.onMessageDialogActionClicked(isOk,bundle);
  if (isOk && bundle != null) {
    String url=bundle.getString(BundleConstant.EXTRA);
    if (!InputHelper.isEmpty(url)) {
      if (ActivityHelper.checkAndRequestReadWritePermission(getActivity())) {
        RestProvider.downloadFile(getContext(),url);
      }
    }
  }
}
