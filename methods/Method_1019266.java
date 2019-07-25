/** 
 * Sends the given messages to the client. 
 */
public void send(Serializable... messages) throws IOException {
  checkState(isRunning(),"send() may only be called when the service is running");
  writer.write(messages);
}
