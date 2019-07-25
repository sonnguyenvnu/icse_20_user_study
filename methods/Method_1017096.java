AsyncFuture<Void> stop(){
  final Map<URI,ClusterNode> clients=this.clients.getAndSet(null);
  if (clients == null) {
    return async.resolved();
  }
  return async.collectAndDiscard(clients.values().stream().map(ClusterNode::close).collect(Collectors.toList()));
}
