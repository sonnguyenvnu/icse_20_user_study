public static void openDocument(MessageObject message,Activity activity,BaseFragment parentFragment){
  if (message == null) {
    return;
  }
  TLRPC.Document document=message.getDocument();
  if (document == null) {
    return;
  }
  File f=null;
  String fileName=message.messageOwner.media != null ? FileLoader.getAttachFileName(document) : "";
  if (message.messageOwner.attachPath != null && message.messageOwner.attachPath.length() != 0) {
    f=new File(message.messageOwner.attachPath);
  }
  if (f == null || f != null && !f.exists()) {
    f=FileLoader.getPathToMessage(message.messageOwner);
  }
  if (f != null && f.exists()) {
    if (parentFragment != null && f.getName().toLowerCase().endsWith("attheme")) {
      Theme.ThemeInfo themeInfo=Theme.applyThemeFile(f,message.getDocumentName(),true);
      if (themeInfo != null) {
        parentFragment.presentFragment(new ThemePreviewActivity(f,themeInfo));
      }
 else {
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
        builder.setMessage(LocaleController.getString("IncorrectTheme",R.string.IncorrectTheme));
        builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
        parentFragment.showDialog(builder.create());
      }
    }
 else {
      String realMimeType=null;
      try {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        MimeTypeMap myMime=MimeTypeMap.getSingleton();
        int idx=fileName.lastIndexOf('.');
        if (idx != -1) {
          String ext=fileName.substring(idx + 1);
          realMimeType=myMime.getMimeTypeFromExtension(ext.toLowerCase());
          if (realMimeType == null) {
            realMimeType=document.mime_type;
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
 catch (          Exception e) {
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
 catch (      Exception e) {
        if (activity == null) {
          return;
        }
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
        builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
        builder.setMessage(LocaleController.formatString("NoHandleAppInstalled",R.string.NoHandleAppInstalled,message.getDocument().mime_type));
        if (parentFragment != null) {
          parentFragment.showDialog(builder.create());
        }
 else {
          builder.show();
        }
      }
    }
  }
}
