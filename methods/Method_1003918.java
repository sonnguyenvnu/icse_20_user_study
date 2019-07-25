@Override public synchronized void connected(K backend,long connectTimeNanos){
  Preconditions.checkNotNull(backend);
  if (!hasBackend(backend))   return;
  strategy.addConnectResult(backend,ConnectionResult.SUCCESS,connectTimeNanos);
}
