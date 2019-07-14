@Override public List<DictDefine> getAllDefine(){
  Map<String,List<DictionaryItemEntity>> items=QueryParamEntity.newQuery().where(DictionaryItemEntity::getStatus,DataStatus.STATUS_ENABLED).noPaging().execute(itemService::select).stream().collect(Collectors.groupingBy(DictionaryItemEntity::getDictId));
  List<DictDefine> all=QueryParamEntity.newQuery().where(DictionaryEntity::getStatus,DataStatus.STATUS_ENABLED).noPaging().execute(dictionaryService::select).stream().map(dict -> DefaultDictDefine.builder().id(dict.getId()).comments(dict.getDescribe()).items((List)items.getOrDefault(dict.getId(),Collections.emptyList())).build()).collect(Collectors.toList());
  all.addAll(super.getAllDefine());
  return all;
}
