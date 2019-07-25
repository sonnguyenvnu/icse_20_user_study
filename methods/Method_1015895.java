/** 
 * Receives a  {@link PluginMessage} from ForgeServer to pass to Client.
 * @param message The message to being received.
 */
public void receive(PluginMessage message) throws IllegalArgumentException {
  state=state.handle(message,con);
}
