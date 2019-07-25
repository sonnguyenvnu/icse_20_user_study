/** 
 * Asks the process to stop. This is best-effort since the process may fail to receive or act on the command.
 */
public void stop(){
  hostname=null;
  if (startSignal != null) {
    startSignal.countDown();
  }
  if (handle != null) {
    handle.stop();
  }
}
