/** 
 * list<message> != null ?????????????
 * @param m Message
 */
private void queueMessage(Message m){
  if (startupMessage != null) {
    startupMessage.add(m);
  }
 else {
    dispatchMessage(m);
  }
}
