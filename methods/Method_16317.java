@Override public List<DictionaryItemEntity> selectByDictId(String dictId){
  if (StringUtils.isNullOrEmpty(dictId)) {
    return new java.util.ArrayList<>();
  }
  return createQuery().where(DictionaryItemEntity.dictId,dictId).orderByAsc(DictionaryItemEntity.sortIndex).listNoPaging();
}
