/** 
 * check whether connection manage feature enabled
 */
private void check(){
  if (!this.switches().isOn(GlobalSwitch.SERVER_MANAGE_CONNECTION_SWITCH)) {
    throw new UnsupportedOperationException("Please enable connection manage feature of Rpc Server before call this method! See comments in constructor RpcServer(int port, boolean manageConnection) to find how to enable!");
  }
}
