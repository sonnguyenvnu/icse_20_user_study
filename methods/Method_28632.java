private Set<Tuple> getBinaryTupledSet(){
  checkIsInMultiOrPipeline();
  List<byte[]> membersWithScores=client.getBinaryMultiBulkReply();
  if (membersWithScores.size() == 0) {
    return Collections.emptySet();
  }
  Set<Tuple> set=new LinkedHashSet<Tuple>(membersWithScores.size() / 2,1.0f);
  Iterator<byte[]> iterator=membersWithScores.iterator();
  while (iterator.hasNext()) {
    set.add(new Tuple(iterator.next(),Double.valueOf(SafeEncoder.encode(iterator.next()))));
  }
  return set;
}
