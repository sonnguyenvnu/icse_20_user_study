/** 
 * Invoked by instrumented code when network access occurs.
 */
public static void notifyOfNetworkEvent(){
  bridge.handleNetworkEvent();
}
