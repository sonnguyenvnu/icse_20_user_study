public static boolean mkfile(final File file,Context context){
  if (file == null)   return false;
  if (file.exists()) {
    return !file.isDirectory();
  }
  try {
    if (file.createNewFile()) {
      return true;
    }
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && FileUtil.isOnExtSdCard(file,context)) {
    DocumentFile document=getDocumentFile(file.getParentFile(),true,context);
    try {
      return document.createFile(MimeTypes.getMimeType(file.getPath(),file.isDirectory()),file.getName()) != null;
    }
 catch (    Exception e) {
      e.printStackTrace();
      return false;
    }
  }
  if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
    try {
      return MediaStoreHack.mkfile(context,file);
    }
 catch (    Exception e) {
      return false;
    }
  }
  return false;
}
