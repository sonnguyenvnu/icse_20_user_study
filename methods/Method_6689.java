public static String copyFileToCache(Uri uri,String ext){
  InputStream inputStream=null;
  FileOutputStream output=null;
  try {
    String name=FileLoader.fixFileName(getFileName(uri));
    if (name == null) {
      int id=SharedConfig.getLastLocalId();
      SharedConfig.saveConfig();
      name=String.format(Locale.US,"%d.%s",id,ext);
    }
    File f=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),"sharing/");
    f.mkdirs();
    f=new File(f,name);
    if (AndroidUtilities.isInternalUri(Uri.fromFile(f))) {
      return null;
    }
    inputStream=ApplicationLoader.applicationContext.getContentResolver().openInputStream(uri);
    output=new FileOutputStream(f);
    byte[] buffer=new byte[1024 * 20];
    int len;
    while ((len=inputStream.read(buffer)) != -1) {
      output.write(buffer,0,len);
    }
    return f.getAbsolutePath();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
 finally {
    try {
      if (inputStream != null) {
        inputStream.close();
      }
    }
 catch (    Exception e2) {
      FileLog.e(e2);
    }
    try {
      if (output != null) {
        output.close();
      }
    }
 catch (    Exception e2) {
      FileLog.e(e2);
    }
  }
  return null;
}
