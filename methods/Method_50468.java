@Override public HmilyTransaction findById(final String id){
  Query query=new Query();
  query.addCriteria(new Criteria("transId").is(id));
  MongoAdapter cache=template.findOne(query,MongoAdapter.class,collectionName);
  return buildByCache(Objects.requireNonNull(cache));
}
