private Set<Tuple> getTupledSet(){
  checkIsInMultiOrPipeline();
  List<String> membersWithScores=client.getMultiBulkReply();
  if (membersWithScores == null) {
    return null;
  }
  if (membersWithScores.size() == 0) {
    return Collections.emptySet();
  }
  Set<Tuple> set=new LinkedHashSet<Tuple>(membersWithScores.size() / 2,1.0f);
  Iterator<String> iterator=membersWithScores.iterator();
  while (iterator.hasNext()) {
    set.add(new Tuple(iterator.next(),Double.valueOf(iterator.next())));
  }
  return set;
}
