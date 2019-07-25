/** 
 * destroy
 */
public void destroy(){
  if (resClient != null) {
    resClient.setConnectionListener(null);
    resClient.setVideoChangeListener(null);
    if (resClient.isStreaming) {
      resClient.stopStreaming();
    }
    if (isRecord()) {
      stopRecord();
    }
    resClient.destroy();
  }
}
