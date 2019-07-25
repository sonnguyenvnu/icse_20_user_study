/** 
 * A publish action was unsuccessful, notify user and update client history
 * @param exception This argument is not used
 */
private void publish(Throwable exception){
  Connection c=Connections.getInstance(context).getConnection(clientHandle);
  @SuppressLint("StringFormatMatches") String action=context.getString(R.string.toast_pub_failed,(Object[])additionalArgs);
  c.addAction(action);
  Notify.toast(context,action,Toast.LENGTH_SHORT);
  System.out.print("Publish failed");
}
