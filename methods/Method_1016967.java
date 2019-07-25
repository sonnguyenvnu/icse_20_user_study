@Override public AsyncFuture<List<URI>> find(){
  return async.resolved(nodes);
}
