@Override public AsyncFuture<ClusterNode> connect(final URI uri){
  return context.resolve(uri.getHost()).map(async::resolved).orElseGet(() -> async.failed(new RuntimeException("Connection refused to: " + uri)));
}
