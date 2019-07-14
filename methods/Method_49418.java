/** 
 * If ES already contains this instance's target index, then do nothing. Otherwise, create the index, then wait  {@link #CREATE_SLEEP}. <p> The  {@code client} field must point to a live, connected client.The  {@code indexName} field must be non-null and point to the nameof the index to check for existence or create.
 * @param index index name
 * @throws IOException if the index status could not be checked or index could not be created
 */
private void checkForOrCreateIndex(String index) throws IOException {
  Preconditions.checkNotNull(client);
  Preconditions.checkNotNull(index);
  if (!useExternalMappings && !client.indexExists(index)) {
    client.createIndex(index,indexSetting);
    try {
      log.debug("Sleeping {} ms after {} index creation returned from actionGet()",createSleep,index);
      Thread.sleep(createSleep);
    }
 catch (    final InterruptedException e) {
      throw new JanusGraphException("Interrupted while waiting for index to settle in",e);
    }
  }
  Preconditions.checkState(client.indexExists(index),"Could not create index: %s",index);
  if (!useMultitypeIndex) {
    client.addAlias(indexName,index);
  }
}
