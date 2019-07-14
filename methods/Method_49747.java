static boolean isProcessCompleted(Process process){
  try {
    if (process == null)     return true;
    process.exitValue();
    return true;
  }
 catch (  IllegalThreadStateException e) {
  }
  return false;
}
