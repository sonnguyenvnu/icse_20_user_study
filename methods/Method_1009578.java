/** 
 * Obtains the synchronous protocol  {@code Executor} and runs the{@link org.fourthline.cling.transport.spi.UpnpStream} directly.
 * @param stream The received {@link org.fourthline.cling.transport.spi.UpnpStream}.
 */
public void received(UpnpStream stream){
  if (!enabled) {
    log.fine("Router disabled, ignoring incoming: " + stream);
    return;
  }
  log.fine("Received synchronous stream: " + stream);
  getConfiguration().getSyncProtocolExecutorService().execute(stream);
}
