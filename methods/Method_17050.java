/** 
 * Returns if the node's value is currently being computed, asynchronously. 
 */
final boolean isComputingAsync(Node<?,?> node){
  return isAsync && !Async.isReady((CompletableFuture<?>)node.getValue());
}
