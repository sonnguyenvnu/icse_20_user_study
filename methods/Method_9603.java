@Override protected void onDialogDismiss(Dialog dialog){
  DownloadController.getInstance(currentAccount).checkAutodownloadSettings();
}
