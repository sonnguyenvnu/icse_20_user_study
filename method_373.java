/** 
 * Get the number of MessageListenerTasks that are currently connected to the JMS provider
 * @return connected task count
 */
private int _XXXXX_(){
  int count=0;
  for (  MessageListenerTask lstTask : pollingTasks) {
    if (lstTask.isConnected()) {
      count++;
    }
  }
  return count;
}