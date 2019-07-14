private void mutateManyUnlogged(final Map<String,Map<StaticBuffer,KCVMutation>> mutations,final StoreTransaction txh) throws BackendException {
  final MaskedTimestamp commitTime=new MaskedTimestamp(txh);
  final Future<Seq<ResultSet>> result=Future.sequence(this.executorService,Iterator.ofAll(mutations.entrySet()).flatMap(tableNameAndMutations -> {
    final String tableName=tableNameAndMutations.getKey();
    final Map<StaticBuffer,KCVMutation> tableMutations=tableNameAndMutations.getValue();
    final CQLKeyColumnValueStore columnValueStore=Option.of(this.openStores.get(tableName)).getOrElseThrow(() -> new IllegalStateException("Store cannot be found: " + tableName));
    return Iterator.ofAll(tableMutations.entrySet()).flatMap(keyAndMutations -> {
      final StaticBuffer key=keyAndMutations.getKey();
      final KCVMutation keyMutations=keyAndMutations.getValue();
      final Iterator<Statement> deletions=Iterator.of(commitTime.getDeletionTime(this.times)).flatMap(deleteTime -> Iterator.ofAll(keyMutations.getDeletions()).map(deletion -> columnValueStore.deleteColumn(key,deletion,deleteTime)));
      final Iterator<Statement> additions=Iterator.of(commitTime.getAdditionTime(this.times)).flatMap(addTime -> Iterator.ofAll(keyMutations.getAdditions()).map(addition -> columnValueStore.insertColumn(key,addition,addTime)));
      return Iterator.concat(deletions,additions).grouped(this.batchSize).map(group -> Future.fromJavaFuture(this.executorService,this.session.executeAsync(new BatchStatement(Type.UNLOGGED).addAll(group).setConsistencyLevel(getTransaction(txh).getWriteConsistencyLevel()))));
    }
);
  }
));
  result.await();
  if (result.isFailure()) {
    throw EXCEPTION_MAPPER.apply(result.getCause().get());
  }
  sleepAfterWrite(txh,commitTime);
}
