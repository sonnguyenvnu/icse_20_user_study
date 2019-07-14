@Override public List<HmilyTransaction> listAllByDelay(final Date date){
  Query query=new Query();
  query.addCriteria(Criteria.where("lastTime").lt(date));
  final List<MongoAdapter> mongoBeans=template.find(query,MongoAdapter.class,collectionName);
  if (CollectionUtils.isNotEmpty(mongoBeans)) {
    return mongoBeans.stream().map(this::buildByCache).collect(Collectors.toList());
  }
  return Collections.emptyList();
}
