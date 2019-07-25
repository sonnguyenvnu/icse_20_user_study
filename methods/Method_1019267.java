/** 
 * Blocks to receive a message sent by the client. 
 */
public Object receive() throws IOException {
  checkState(isRunning(),"receive() may only be called when the service is running");
  return reader.read();
}
