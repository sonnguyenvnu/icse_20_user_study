/** 
 * Returns if the future has successfully completed. 
 */
static boolean isReady(@Nullable CompletableFuture<?> future){
  return (future != null) && future.isDone() && !future.isCompletedExceptionally() && (future.join() != null);
}
