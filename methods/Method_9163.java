private static File getLogFile(long callID){
  if (BuildVars.DEBUG_VERSION) {
    File debugLogsDir=new File(ApplicationLoader.applicationContext.getExternalFilesDir(null),"logs");
    String[] logs=debugLogsDir.list();
    if (logs != null) {
      for (      String log : logs) {
        if (log.endsWith("voip" + callID + ".txt")) {
          return new File(debugLogsDir,log);
        }
      }
    }
  }
  return new File(getLogsDir(),callID + ".log");
}
