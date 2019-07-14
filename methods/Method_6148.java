public static void openForView(TLObject media,Activity activity){
  if (media == null || activity == null) {
    return;
  }
  String fileName=FileLoader.getAttachFileName(media);
  File f=FileLoader.getPathToAttach(media,true);
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
        if (media instanceof TLRPC.TL_document) {
          realMimeType=((TLRPC.TL_document)media).mime_type;
        }
        if (realMimeType == null || realMimeType.length() == 0) {
          realMimeType=null;
        }
      }
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
