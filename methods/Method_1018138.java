/** 
 * Tries to kill the process. This will not send a  {@link #stop()} message to the process so it's recommended that users first try to stop the process cleanly and only resort to thismethod if that fails.
 */
public void destroy(){
  hostname=null;
  if (startSignal != null) {
    startSignal.countDown();
  }
  if (handle != null) {
    handle.kill();
  }
}
