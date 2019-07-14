public static void openForView(MessageObject message,final Activity activity){
  File f=null;
  String fileName=message.getFileName();
  if (message.messageOwner.attachPath != null && message.messageOwner.attachPath.length() != 0) {
    f=new File(message.messageOwner.attachPath);
  }
  if (f == null || !f.exists()) {
    f=FileLoader.getPathToMessage(message.messageOwner);
  }
  if (f != null && f.exists()) {
    String realMimeType=null;
    Intent intent=new Intent(Intent.ACTION_VIEW);
    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    MimeTypeMap myMime=MimeTypeMap.getSingleton();
    int idx=fileName.lastIndexOf('.');
    if (idx != -1) {
      String ext=fileName.substring(idx + 1);
      realMimeType=myMime.getMimeTypeFromExtension(ext.toLowerCase());
      if (realMimeType == null) {
        if (message.type == 9 || message.type == 0) {
          realMimeType=message.getDocument().mime_type;
        }
        if (realMimeType == null || realMimeType.length() == 0) {
          realMimeType=null;
        }
      }
    }
    if (Build.VERSION.SDK_INT >= 26 && realMimeType != null && realMimeType.equals("application/vnd.android.package-archive") && !ApplicationLoader.applicationContext.getPackageManager().canRequestPackageInstalls()) {
      AlertDialog.Builder builder=new AlertDialog.Builder(activity);
      builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
      builder.setMessage(LocaleController.getString("ApkRestricted",R.string.ApkRestricted));
      builder.setPositiveButton(LocaleController.getString("PermissionOpenSettings",R.string.PermissionOpenSettings),(dialogInterface,i) -> {
        try {
          activity.startActivity(new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,Uri.parse("package:" + activity.getPackageName())));
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
);
      builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
      builder.show();
      return;
    }
    if (Build.VERSION.SDK_INT >= 24) {
      intent.setDataAndType(FileProvider.getUriForFile(activity,BuildConfig.APPLICATION_ID + ".provider",f),realMimeType != null ? realMimeType : "text/plain");
    }
 else {
      intent.setDataAndType(Uri.fromFile(f),realMimeType != null ? realMimeType : "text/plain");
    }
    if (realMimeType != null) {
      try {
        activity.startActivityForResult(intent,500);
      }
 catch (      Exception e) {
        if (Build.VERSION.SDK_INT >= 24) {
          intent.setDataAndType(FileProvider.getUriForFile(activity,BuildConfig.APPLICATION_ID + ".provider",f),"text/plain");
        }
 else {
          intent.setDataAndType(Uri.fromFile(f),"text/plain");
        }
        activity.startActivityForResult(intent,500);
      }
    }
 else {
      activity.startActivityForResult(intent,500);
    }
  }
}
