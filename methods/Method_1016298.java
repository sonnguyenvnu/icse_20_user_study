/** 
 * This method is used by  {@link #dispatchMessage(float,Telegraph,Telegraph,int,Object) dispatchMessage} for immediatetelegrams and  {@link #update(float) update} for delayed telegrams. It first calls the message handling method of thereceiving agents with the specified telegram then returns the telegram to the pool.
 * @param telegram the telegram to discharge 
 */
private void discharge(Telegram telegram){
  if (telegram.receiver != null) {
    if (!telegram.receiver.handleMessage(telegram)) {
      if (debugEnabled)       GdxAI.getLogger().info(LOG_TAG,"Message " + telegram.message + " not handled");
    }
  }
 else {
    int handledCount=0;
    Array<Telegraph> listeners=msgListeners.get(telegram.message);
    if (listeners != null) {
      for (int i=0; i < listeners.size; i++) {
        if (listeners.get(i).handleMessage(telegram)) {
          handledCount++;
        }
      }
    }
    if (debugEnabled && handledCount == 0)     GdxAI.getLogger().info(LOG_TAG,"Message " + telegram.message + " not handled");
  }
  if (telegram.returnReceiptStatus == Telegram.RETURN_RECEIPT_NEEDED) {
    telegram.receiver=telegram.sender;
    telegram.sender=this;
    telegram.returnReceiptStatus=Telegram.RETURN_RECEIPT_SENT;
    discharge(telegram);
  }
 else {
    POOL.free(telegram);
  }
}
