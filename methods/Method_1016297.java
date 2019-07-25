/** 
 * Dispatches any delayed telegrams with a timestamp that has expired. Dispatched telegrams are removed from the queue. <p> This method must be called regularly from inside the main game loop to facilitate the correct and timely dispatch of any delayed messages. Notice that the message dispatcher internally calls  {@link Timepiece#getTime() GdxAI.getTimepiece().getTime()} to get the current AI time and properly dispatch delayed messages. This means that<ul> <li>if you forget to  {@link Timepiece#update(float) update the timepiece} the delayed messages won't be dispatched.</li><li>ideally the timepiece should be updated before the message dispatcher.</li> </ul> 
 */
public void update(){
  float currentTime=GdxAI.getTimepiece().getTime();
  Telegram telegram;
  while ((telegram=queue.peek()) != null) {
    if (telegram.getTimestamp() > currentTime)     break;
    if (debugEnabled) {
      GdxAI.getLogger().info(LOG_TAG,"Queued telegram ready for dispatch: Sent to " + telegram.receiver + ". Message code is " + telegram.message);
    }
    discharge(telegram);
    queue.poll();
  }
}
