@Override public DictionaryItemEntity deleteByPk(String id){
  DictionaryItemEntity entity=selectByPk(id);
  if (null != entity) {
    clearDictCache(entity.getDictId());
  }
  return super.deleteByPk(id);
}
