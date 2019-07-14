@Override public int updateByPk(String id,DictionaryItemEntity entity){
  clearDictCache(entity.getDictId());
  return super.updateByPk(id,entity);
}
