private void showDownloadAlert(){
  AlertDialog.Builder builder=new AlertDialog.Builder(parentActivity);
  builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
  builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
  boolean alreadyDownloading=currentMessageObject != null && currentMessageObject.isVideo() && FileLoader.getInstance(currentMessageObject.currentAccount).isLoadingFile(currentFileNames[0]);
  if (alreadyDownloading) {
    builder.setMessage(LocaleController.getString("PleaseStreamDownload",R.string.PleaseStreamDownload));
  }
 else {
    builder.setMessage(LocaleController.getString("PleaseDownload",R.string.PleaseDownload));
  }
  showAlertDialog(builder);
}
