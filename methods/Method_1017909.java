@Override public List<Predicate> augment(Predicate[] predicate){
  LOG.debug("FeedAclIndexQueryAugmentor.augment(Predicate[])");
  QOpsManagerFeedId feed=getOpsManagerFeedId();
  BooleanExpression exists=generateExistsExpression(feed);
  List<Predicate> predicates=new ArrayList<>();
  predicates.addAll(Arrays.asList(predicate));
  predicates.add(exists);
  return predicates;
}
