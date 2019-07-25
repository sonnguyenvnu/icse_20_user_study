/** 
 * A addNewSubscription action has been successfully completed, update the connection object associated with the client this action belongs to and then notify the user of success
 */
private void subscribe(){
  Connection c=Connections.getInstance(context).getConnection(clientHandle);
  String actionTaken=context.getString(R.string.toast_sub_success,(Object[])additionalArgs);
  c.addAction(actionTaken);
  Notify.toast(context,actionTaken,Toast.LENGTH_SHORT);
  System.out.print(actionTaken);
}
