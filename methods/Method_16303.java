@Override @Cacheable(key="'dictDefineById:'+#id") public DictDefine getDefine(String id){
  DictionaryEntity entity=dictionaryService.selectByPk(id);
  if (entity == null) {
    return super.getDefine(id);
  }
  List<EnumDict<Object>> items=(List)itemService.selectByDictId(id).stream().filter(e -> DataStatus.STATUS_ENABLED.equals(e.getStatus())).collect(Collectors.toList());
  return DefaultDictDefine.builder().id(id).comments(entity.getDescribe()).items(items).build();
}
