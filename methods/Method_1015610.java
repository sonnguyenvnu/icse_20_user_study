/** 
 * Takes all messages from the queue, adds them to the hashmap and then sends all bundled messages 
 */
protected void drain(){
  Message msg;
  while ((msg=queue.poll()) != null)   addAndSendIfSizeExceeded(msg);
  _sendBundledMessages();
}
