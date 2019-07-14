/** 
 * Set next message to queue. Clears queue before.
 * @param message the message to set
 */
private void setNextMessage(int message){
  clearMessages();
  animationHandler.sendEmptyMessage(message);
}
