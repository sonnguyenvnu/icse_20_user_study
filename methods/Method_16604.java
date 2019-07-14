@Override public RelationDefineEntity deleteByPk(String pk){
  publisher.publishEvent(new ClearPersonCacheEvent());
  return super.deleteByPk(pk);
}
