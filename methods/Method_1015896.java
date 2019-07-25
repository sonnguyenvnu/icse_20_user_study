/** 
 * Receives a  {@link PluginMessage} from ForgeClientData to pass to Server.
 * @param message The message to being received.
 */
public void receive(PluginMessage message) throws IllegalArgumentException {
  state=state.handle(message,ch);
}
