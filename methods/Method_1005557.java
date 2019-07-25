/** 
 * Disconnect from the server
 * @param clientHandle identifies the MqttConnection to use
 * @param invocationContext arbitrary data to be passed back to the application
 * @param activityToken arbitrary identifier to be passed back to the Activity
 */
public void disconnect(String clientHandle,String invocationContext,String activityToken){
  MqttConnection client=getConnection(clientHandle);
  client.disconnect(invocationContext,activityToken);
  connections.remove(clientHandle);
  stopSelf();
}
