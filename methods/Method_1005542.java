/** 
 * A publish action has been successfully completed, update connection object associated with the client this action belongs to, then notify the user of success
 */
private void publish(){
  Connection c=Connections.getInstance(context).getConnection(clientHandle);
  @SuppressLint("StringFormatMatches") String actionTaken=context.getString(R.string.toast_pub_success,(Object[])additionalArgs);
  c.addAction(actionTaken);
  Notify.toast(context,actionTaken,Toast.LENGTH_SHORT);
  System.out.print("Published");
}
