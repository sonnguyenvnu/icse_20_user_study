private String getLogFilePath(long callID){
  File dir=VoIPHelper.getLogsDir();
  if (!BuildVars.DEBUG_VERSION) {
    File[] _logs=dir.listFiles();
    ArrayList<File> logs=new ArrayList<>();
    logs.addAll(Arrays.asList(_logs));
    while (logs.size() > 20) {
      File oldest=logs.get(0);
      for (      File file : logs) {
        if (file.getName().endsWith(".log") && file.lastModified() < oldest.lastModified())         oldest=file;
      }
      oldest.delete();
      logs.remove(oldest);
    }
  }
  return new File(dir,callID + ".log").getAbsolutePath();
}
