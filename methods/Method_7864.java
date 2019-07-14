private void onSharePressed(){
  if (parentActivity == null || currentMedia == null) {
    return;
  }
  try {
    File f=getMediaFile(currentIndex);
    if (f != null && f.exists()) {
      Intent intent=new Intent(Intent.ACTION_SEND);
      intent.setType(getMediaMime(currentIndex));
      if (Build.VERSION.SDK_INT >= 24) {
        try {
          intent.putExtra(Intent.EXTRA_STREAM,FileProvider.getUriForFile(parentActivity,BuildConfig.APPLICATION_ID + ".provider",f));
          intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
 catch (        Exception ignore) {
          intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(f));
        }
      }
 else {
        intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(f));
      }
      parentActivity.startActivityForResult(Intent.createChooser(intent,LocaleController.getString("ShareFile",R.string.ShareFile)),500);
    }
 else {
      AlertDialog.Builder builder=new AlertDialog.Builder(parentActivity);
      builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
      builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
      builder.setMessage(LocaleController.getString("PleaseDownload",R.string.PleaseDownload));
      showDialog(builder.create());
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
