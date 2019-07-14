@Override public List<HmilyTransaction> listAll(){
  final List<MongoAdapter> resultList=template.findAll(MongoAdapter.class,collectionName);
  if (CollectionUtils.isNotEmpty(resultList)) {
    return resultList.stream().map(this::buildByCache).collect(Collectors.toList());
  }
  return Collections.emptyList();
}
