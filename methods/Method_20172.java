/** 
 * Create a new looper that runs on a new background thread.
 */
public static Looper buildBackgroundLooper(String threadName){
  HandlerThread handlerThread=new HandlerThread(threadName);
  handlerThread.start();
  return handlerThread.getLooper();
}
