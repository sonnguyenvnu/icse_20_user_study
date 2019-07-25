/** 
 * A addNewSubscription action was unsuccessful, notify user and update client history
 * @param exception This argument is not used
 */
private void subscribe(Throwable exception){
  Connection c=Connections.getInstance(context).getConnection(clientHandle);
  String action=context.getString(R.string.toast_sub_failed,(Object[])additionalArgs);
  c.addAction(action);
  Notify.toast(context,action,Toast.LENGTH_SHORT);
  System.out.print(action);
}
