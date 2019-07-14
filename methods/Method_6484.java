public static String getNetworkLogPath(){
  if (!BuildVars.LOGS_ENABLED) {
    return "";
  }
  try {
    File sdCard=ApplicationLoader.applicationContext.getExternalFilesDir(null);
    if (sdCard == null) {
      return "";
    }
    File dir=new File(sdCard.getAbsolutePath() + "/logs");
    dir.mkdirs();
    getInstance().networkFile=new File(dir,getInstance().dateFormat.format(System.currentTimeMillis()) + "_net.txt");
    return getInstance().networkFile.getAbsolutePath();
  }
 catch (  Throwable e) {
    e.printStackTrace();
  }
  return "";
}
