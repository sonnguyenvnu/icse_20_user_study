@Override public AsyncFuture<Void> configure(){
  return async.collectAndDiscard(run(b -> b.configure()));
}
