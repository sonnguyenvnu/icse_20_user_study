/** 
 * ???????????
 * @param event ??
 */
public static void post(final Event event){
  if (!isEnable()) {
    return;
  }
  CopyOnWriteArraySet<Subscriber> subscribers=SUBSCRIBER_MAP.get(event.getClass());
  if (CommonUtils.isNotEmpty(subscribers)) {
    for (    final Subscriber subscriber : subscribers) {
      if (subscriber.isSync()) {
        handleEvent(subscriber,event);
      }
 else {
        final RpcInternalContext context=RpcInternalContext.peekContext();
        final ThreadPoolExecutor asyncThreadPool=AsyncRuntime.getAsyncThreadPool();
        try {
          asyncThreadPool.execute(new Runnable(){
            @Override public void run(){
              try {
                RpcInternalContext.setContext(context);
                handleEvent(subscriber,event);
              }
  finally {
                RpcInternalContext.removeContext();
              }
            }
          }
);
        }
 catch (        RejectedExecutionException e) {
          LOGGER.warn("This queue is full when post event to async execute, queue size is " + asyncThreadPool.getQueue().size() + ", please optimize this async thread pool of eventbus.");
        }
      }
    }
  }
}
