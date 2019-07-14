/** 
 * - maintain an open connection with the client - on initial connection send latest data of each requested event type - subsequently send all changes for each requested event type
 * @param request  incoming HTTP Request
 * @param response outgoing HTTP Response (as a streaming response)
 * @throws javax.servlet.ServletException
 * @throws java.io.IOException
 */
private void handleRequest(HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {
  final AtomicBoolean moreDataWillBeSent=new AtomicBoolean(true);
  Subscription sampleSubscription=null;
  int numberConnections=incrementAndGetCurrentConcurrentConnections();
  try {
    int maxNumberConnectionsAllowed=getMaxNumberConcurrentConnectionsAllowed();
    if (numberConnections > maxNumberConnectionsAllowed) {
      response.sendError(503,"MaxConcurrentConnections reached: " + maxNumberConnectionsAllowed);
    }
 else {
      response.setHeader("Content-Type","text/event-stream;charset=UTF-8");
      response.setHeader("Cache-Control","no-cache, no-store, max-age=0, must-revalidate");
      response.setHeader("Pragma","no-cache");
      final PrintWriter writer=response.getWriter();
      sampleSubscription=sampleStream.observeOn(Schedulers.io()).subscribe(new Subscriber<String>(){
        @Override public void onCompleted(){
          logger.error("HystrixSampleSseServlet: ({}) received unexpected OnCompleted from sample stream",getClass().getSimpleName());
          moreDataWillBeSent.set(false);
        }
        @Override public void onError(        Throwable e){
          moreDataWillBeSent.set(false);
        }
        @Override public void onNext(        String sampleDataAsString){
          if (sampleDataAsString != null) {
            try {
              writer.print("data: " + sampleDataAsString + "\n\n");
              if (writer.checkError()) {
                moreDataWillBeSent.set(false);
              }
              writer.flush();
            }
 catch (            Exception ex) {
              moreDataWillBeSent.set(false);
            }
          }
        }
      }
);
      while (moreDataWillBeSent.get() && !isDestroyed) {
        try {
          Thread.sleep(pausePollerThreadDelayInMs);
          writer.print("ping: \n\n");
          if (writer.checkError()) {
            moreDataWillBeSent.set(false);
          }
          writer.flush();
        }
 catch (        Exception ex) {
          moreDataWillBeSent.set(false);
        }
      }
    }
  }
  finally {
    decrementCurrentConcurrentConnections();
    if (sampleSubscription != null && !sampleSubscription.isUnsubscribed()) {
      sampleSubscription.unsubscribe();
    }
  }
}
