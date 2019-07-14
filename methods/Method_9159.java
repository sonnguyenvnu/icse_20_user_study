public static File getLogsDir(){
  File logsDir=new File(ApplicationLoader.applicationContext.getCacheDir(),"voip_logs");
  if (!logsDir.exists())   logsDir.mkdirs();
  return logsDir;
}
