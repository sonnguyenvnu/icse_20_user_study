/** 
 * Fetches the state
 * @param state_timeout
 */
public final void start(long state_timeout) throws Exception {
  channel.getState(null,state_timeout);
}
