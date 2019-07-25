/** 
 * Shuts down this  {@link AccessLogWriter}.
 * @return the {@link CompletableFuture} which is completedwhen this  {@link AccessLogWriter} has been shut down.
 */
default CompletableFuture<Void> shutdown(){
  return CompletableFuture.completedFuture(null);
}
