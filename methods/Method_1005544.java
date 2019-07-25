/** 
 * A disconnection action has been successfully completed, update the connection object associated with the client this action belongs to and then notify the user of success.
 */
private void disconnect(){
  Connection c=Connections.getInstance(context).getConnection(clientHandle);
  c.changeConnectionStatus(Connection.ConnectionStatus.DISCONNECTED);
  String actionTaken=context.getString(R.string.toast_disconnected);
  c.addAction(actionTaken);
  Log.i(TAG,c.handle() + " disconnected.");
  Intent intent=new Intent();
  intent.setClassName(context,activityClass);
  intent.putExtra("handle",clientHandle);
}
