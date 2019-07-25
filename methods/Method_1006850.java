/** 
 * @param status
 * @return
 */
private int severity(ExitStatus status){
  if (status.exitCode.startsWith(EXECUTING.exitCode)) {
    return 1;
  }
  if (status.exitCode.startsWith(COMPLETED.exitCode)) {
    return 2;
  }
  if (status.exitCode.startsWith(NOOP.exitCode)) {
    return 3;
  }
  if (status.exitCode.startsWith(STOPPED.exitCode)) {
    return 4;
  }
  if (status.exitCode.startsWith(FAILED.exitCode)) {
    return 5;
  }
  if (status.exitCode.startsWith(UNKNOWN.exitCode)) {
    return 6;
  }
  return 7;
}
