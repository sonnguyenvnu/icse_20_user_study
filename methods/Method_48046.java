@Override public void mutateMany(Map<String,Map<StaticBuffer,KCVMutation>> batch,StoreTransaction txh) throws BackendException {
  MutationBatch m=keyspaceContext.getClient().prepareMutationBatch().withAtomicBatch(atomicBatch).setConsistencyLevel(getTx(txh).getWriteConsistencyLevel().getAstyanax()).withRetryPolicy(retryPolicy.duplicate());
  final MaskedTimestamp commitTime=new MaskedTimestamp(txh);
  for (  Map.Entry<String,Map<StaticBuffer,KCVMutation>> batchentry : batch.entrySet()) {
    String storeName=batchentry.getKey();
    Preconditions.checkArgument(openStores.containsKey(storeName),"Store cannot be found: " + storeName);
    ColumnFamily<ByteBuffer,ByteBuffer> columnFamily=openStores.get(storeName).getColumnFamily();
    Map<StaticBuffer,KCVMutation> mutations=batchentry.getValue();
    for (    Map.Entry<StaticBuffer,KCVMutation> ent : mutations.entrySet()) {
      KCVMutation janusgraphMutation=ent.getValue();
      ByteBuffer key=ent.getKey().asByteBuffer();
      if (janusgraphMutation.hasDeletions()) {
        ColumnListMutation<ByteBuffer> deletions=m.withRow(columnFamily,key);
        deletions.setTimestamp(commitTime.getDeletionTime(times));
        for (        StaticBuffer b : janusgraphMutation.getDeletions())         deletions.deleteColumn(b.as(StaticBuffer.BB_FACTORY));
      }
      if (janusgraphMutation.hasAdditions()) {
        ColumnListMutation<ByteBuffer> updates=m.withRow(columnFamily,key);
        updates.setTimestamp(commitTime.getAdditionTime(times));
        for (        Entry e : janusgraphMutation.getAdditions()) {
          Integer ttl=(Integer)e.getMetaData().get(EntryMetaData.TTL);
          if (null != ttl && ttl > 0) {
            updates.putColumn(e.getColumnAs(StaticBuffer.BB_FACTORY),e.getValueAs(StaticBuffer.BB_FACTORY),ttl);
          }
 else {
            updates.putColumn(e.getColumnAs(StaticBuffer.BB_FACTORY),e.getValueAs(StaticBuffer.BB_FACTORY));
          }
        }
      }
    }
  }
  try {
    m.execute();
  }
 catch (  ConnectionException e) {
    throw new TemporaryBackendException(e);
  }
  sleepAfterWrite(txh,commitTime);
}
