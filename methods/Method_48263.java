void mutate(KCVSCache store,StaticBuffer key,List<Entry> additions,List<Entry> deletions) throws BackendException {
  Preconditions.checkNotNull(store);
  if (additions.isEmpty() && deletions.isEmpty())   return;
  KCVEntryMutation m=new KCVEntryMutation(additions,deletions);
  final Map<StaticBuffer,KCVEntryMutation> storeMutation=mutations.computeIfAbsent(store,k -> new HashMap<>());
  KCVEntryMutation existingM=storeMutation.get(key);
  if (existingM != null) {
    existingM.merge(m);
  }
 else {
    storeMutation.put(key,m);
  }
  numMutations+=m.getTotalMutations();
  if (batchLoading && numMutations >= persistChunkSize) {
    flushInternal();
  }
}
