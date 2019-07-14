/** 
 * Add a  {@link TimerListener} that will be executed until it is garbage collected or removed by clearing the returned {@link Reference}. <p> NOTE: It is the responsibility of code that adds a listener via this method to clear this listener when completed. <p> <blockquote> <pre>  {@code // add a TimerListener  Reference<TimerListener> listener = HystrixTimer.getInstance().addTimerListener(listenerImpl); // sometime later, often in a thread shutdown, request cleanup, servlet filter or something similar the listener must be shutdown via the clear() method listener.clear();}</pre> </blockquote>
 * @param listener TimerListener implementation that will be triggered according to its <code>getIntervalTimeInMilliseconds()</code> method implementation.
 * @return reference to the TimerListener that allows cleanup via the <code>clear()</code> method
 */
public Reference<TimerListener> addTimerListener(final TimerListener listener){
  startThreadIfNeeded();
  Runnable r=new Runnable(){
    @Override public void run(){
      try {
        listener.tick();
      }
 catch (      Exception e) {
        logger.error("Failed while ticking TimerListener",e);
      }
    }
  }
;
  ScheduledFuture<?> f=executor.get().getThreadPool().scheduleAtFixedRate(r,listener.getIntervalTimeInMilliseconds(),listener.getIntervalTimeInMilliseconds(),TimeUnit.MILLISECONDS);
  return new TimerReference(listener,f);
}
