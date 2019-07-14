/** 
 * Captures and returns a bug report. The bug report contains some device information and the logcat.
 * @return a String containing the bug report.
 * @throws IOException when any I/O error occur.
 */
@NonNull public String getBugReport() throws IOException {
  String logcat=getLogcat();
  String deviceInfo=getDeviceInfo();
  String log="---------- BUG REPORT BEGINS ----------\n";
  log+=deviceInfo + "\n" + logcat;
  log+="---------- BUG REPORT ENDS ------------\n";
  return log;
}
