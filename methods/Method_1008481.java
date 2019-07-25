final void refresh(String source,SearcherScope scope) throws EngineException {
  final long bytes=indexWriter.ramBytesUsed();
  writingBytes.addAndGet(bytes);
  try (ReleasableLock lock=readLock.acquire()){
    ensureOpen();
switch (scope) {
case EXTERNAL:
      externalSearcherManager.maybeRefreshBlocking();
    break;
case INTERNAL:
  internalSearcherManager.maybeRefreshBlocking();
break;
default :
throw new IllegalArgumentException("unknown scope: " + scope);
}
}
 catch (AlreadyClosedException e) {
failOnTragicEvent(e);
throw e;
}
catch (Exception e) {
try {
failEngine("refresh failed source[" + source + "]",e);
}
 catch (Exception inner) {
e.addSuppressed(inner);
}
throw new RefreshFailedEngineException(shardId,e);
}
 finally {
writingBytes.addAndGet(-bytes);
}
maybePruneDeletedTombstones();
mergeScheduler.refreshConfig();
}
