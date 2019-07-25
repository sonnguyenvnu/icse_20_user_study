public Set<String> merge(Record apolloRecord){
  final Record oldRecord=recordMap.get(apolloRecord.key());
  if (oldRecord == null) {
    recordMap.put(apolloRecord.key(),apolloRecord);
    return Collections.emptySet();
  }
 else {
    return oldRecord.mergeWith(apolloRecord);
  }
}
