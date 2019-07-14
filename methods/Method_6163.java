public static File getFilesDirFixed(){
  for (int a=0; a < 10; a++) {
    File path=ApplicationLoader.applicationContext.getFilesDir();
    if (path != null) {
      return path;
    }
  }
  try {
    ApplicationInfo info=applicationContext.getApplicationInfo();
    File path=new File(info.dataDir,"files");
    path.mkdirs();
    return path;
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return new File("/data/data/org.telegram.messenger/files");
}
