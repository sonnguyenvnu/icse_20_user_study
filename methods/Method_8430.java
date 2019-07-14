public static boolean openApkInstall(Activity activity,TLRPC.Document document){
  boolean exists=false;
  try {
    String fileName=FileLoader.getAttachFileName(document);
    File f=FileLoader.getPathToAttach(document,true);
    if (exists=f.exists()) {
      Intent intent=new Intent(Intent.ACTION_VIEW);
      intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      if (Build.VERSION.SDK_INT >= 24) {
        intent.setDataAndType(FileProvider.getUriForFile(activity,BuildConfig.APPLICATION_ID + ".provider",f),"application/vnd.android.package-archive");
      }
 else {
        intent.setDataAndType(Uri.fromFile(f),"application/vnd.android.package-archive");
      }
      try {
        activity.startActivityForResult(intent,500);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return exists;
}
