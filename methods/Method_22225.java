/** 
 * Executes the logcat command with arguments taken from  {@link org.acra.annotation.AcraCore#logcatArguments()}
 * @param bufferName The name of the buffer to be read: "main" (default), "radio" or "events".
 * @return A string containing the latest lines of the output.Default is 100 lines, use "-t", "300" in  {@link org.acra.annotation.AcraCore#logcatArguments()} if you want 300 lines.You should be aware that increasing this value causes a longer report generation time and a bigger footprint on the device data plan consumption.
 */
private String collectLogCat(@NonNull CoreConfiguration config,@Nullable String bufferName) throws IOException {
  final int myPid=android.os.Process.myPid();
  final String myPidStr=Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN && config.logcatFilterByPid() && myPid > 0 ? Integer.toString(myPid) + "):" : null;
  final List<String> commandLine=new ArrayList<>();
  commandLine.add("logcat");
  if (bufferName != null) {
    commandLine.add("-b");
    commandLine.add(bufferName);
  }
  final int tailCount;
  final List<String> logcatArgumentsList=config.logcatArguments();
  final int tailIndex=logcatArgumentsList.indexOf("-t");
  if (tailIndex > -1 && tailIndex < logcatArgumentsList.size()) {
    tailCount=Integer.parseInt(logcatArgumentsList.get(tailIndex + 1));
  }
 else {
    tailCount=-1;
  }
  commandLine.addAll(logcatArgumentsList);
  final Process process=new ProcessBuilder().command(commandLine).redirectErrorStream(true).start();
  if (ACRA.DEV_LOGGING)   ACRA.log.d(LOG_TAG,"Retrieving logcat output (buffer:" + (bufferName == null ? "default" : bufferName) + ")...");
  try {
    return streamToString(config,process.getInputStream(),myPidStr == null ? null : s -> s.contains(myPidStr),tailCount);
  }
  finally {
    process.destroy();
  }
}
