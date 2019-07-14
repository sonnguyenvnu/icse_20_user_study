/** 
 * Marks the message as processed. Should only be called by a  {@link Sender} and may be calledmultiple times.
 * @param isDelivered Whether the message has been delivered to its target. The message isconsidered as being delivered when this method has been called with  {@code isDelivered} setto true at least once.
 */
public synchronized void markAsProcessed(boolean isDelivered){
  this.isDelivered|=isDelivered;
  isProcessed=true;
  notifyAll();
}
