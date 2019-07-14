/** 
 * Maintain an open connection with the client. On initial connection send latest data of each requested event type and subsequently send all changes for each requested event type.
 * @return JAX-RS Response - Serialization will be handled by {@link HystrixStreamingOutputProvider}
 */
protected Response handleRequest(){
  ResponseBuilder builder=null;
  int numberConnections=getCurrentConnections().get();
  int maxNumberConnectionsAllowed=getMaxNumberConcurrentConnectionsAllowed();
  if (numberConnections >= maxNumberConnectionsAllowed) {
    builder=Response.status(Status.SERVICE_UNAVAILABLE).entity("MaxConcurrentConnections reached: " + maxNumberConnectionsAllowed);
  }
 else {
    builder=Response.status(Status.OK);
    builder.header(HttpHeaders.CONTENT_TYPE,"text/event-stream;charset=UTF-8");
    builder.header(HttpHeaders.CACHE_CONTROL,"no-cache, no-store, max-age=0, must-revalidate");
    builder.header("Pragma","no-cache");
    getCurrentConnections().incrementAndGet();
    builder.entity(new HystrixStream(sampleStream,pausePollerThreadDelayInMs,getCurrentConnections()));
  }
  return builder.build();
}
