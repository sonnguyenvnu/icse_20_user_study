/** 
 * Process a notification that we have disconnected
 * @param data
 */
private void disconnected(Bundle data){
  clientHandle=null;
  IMqttToken token=removeMqttToken(data);
  if (token != null) {
    ((MqttTokenAndroid)token).notifyComplete();
  }
  if (callback != null) {
    callback.connectionLost(null);
  }
}
