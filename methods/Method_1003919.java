@Override public synchronized void released(K backend){
  Preconditions.checkNotNull(backend);
  if (!hasBackend(backend))   return;
  strategy.connectionReturned(backend);
}
