private void onSharePressed(){
  if (parentActivity == null || !allowShare) {
    return;
  }
  try {
    File f=null;
    boolean isVideo=false;
    if (currentMessageObject != null) {
      isVideo=currentMessageObject.isVideo();
      if (!TextUtils.isEmpty(currentMessageObject.messageOwner.attachPath)) {
        f=new File(currentMessageObject.messageOwner.attachPath);
        if (!f.exists()) {
          f=null;
        }
      }
      if (f == null) {
        f=FileLoader.getPathToMessage(currentMessageObject.messageOwner);
      }
    }
 else     if (currentFileLocation != null) {
      f=FileLoader.getPathToAttach(currentFileLocation.location,avatarsDialogId != 0 || isEvent);
    }
    if (f.exists()) {
      Intent intent=new Intent(Intent.ACTION_SEND);
      if (isVideo) {
        intent.setType("video/mp4");
      }
 else {
        if (currentMessageObject != null) {
          intent.setType(currentMessageObject.getMimeType());
        }
 else {
          intent.setType("image/jpeg");
        }
      }
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
      showDownloadAlert();
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
