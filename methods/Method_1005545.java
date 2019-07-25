/** 
 * A connection action has been successfully completed, update the connection object associated with the client this action belongs to and then notify the user of success.
 */
private void connect(){
  Connection c=Connections.getInstance(context).getConnection(clientHandle);
  c.changeConnectionStatus(Connection.ConnectionStatus.CONNECTED);
  c.addAction("Client Connected");
  Log.i(TAG,c.handle() + " connected.");
  try {
    ArrayList<Subscription> subscriptions=connection.getSubscriptions();
    for (    Subscription sub : subscriptions) {
      Log.i(TAG,"Auto-subscribing to: " + sub.getTopic() + "@ QoS: " + sub.getQos());
      connection.getClient().subscribe(sub.getTopic(),sub.getQos());
    }
  }
 catch (  MqttException ex) {
    Log.e(TAG,"Failed to Auto-Subscribe: " + ex.getMessage());
  }
}
