public synchronized void close(final String reason,boolean delete) throws IOException {
  if (closed.compareAndSet(false,true)) {
    deleted.compareAndSet(false,delete);
    try {
      final Set<Integer> shardIds=shardIds();
      for (      final int shardId : shardIds) {
        try {
          removeShard(shardId,reason);
        }
 catch (        Exception e) {
          logger.warn("failed to close shard",e);
        }
      }
    }
  finally {
      IOUtils.close(bitsetFilterCache,tokenRangesBitsetFilterCache,indexCache,indexFieldData,mapperService,refreshTask,trimTranslogTask);
    }
  }
}
