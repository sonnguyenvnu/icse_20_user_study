@Override public Boolean batchRemove(final List<String> ids,final String applicationName){
  if (CollectionUtils.isEmpty(ids) || StringUtils.isBlank(applicationName)) {
    return Boolean.FALSE;
  }
  final String mongoTableName=RepositoryPathUtils.buildMongoTableName(applicationName);
  ids.forEach(id -> {
    Query query=new Query();
    query.addCriteria(new Criteria("transId").is(id));
    mongoTemplate.remove(query,mongoTableName);
  }
);
  return Boolean.TRUE;
}
