private int severity(ExitStatus status){
  return status.exitCode.startsWith(EXECUTING.exitCode) ? 1 : (status.exitCode.startsWith(COMPLETED.exitCode) ? 2 : (status.exitCode.startsWith(NOOP.exitCode) ? 3 : (status.exitCode.startsWith(STOPPED.exitCode) ? 4 : (status.exitCode.startsWith(FAILED.exitCode) ? 5 : (status.exitCode.startsWith(UNKNOWN.exitCode) ? 6 : 7)))));
}
