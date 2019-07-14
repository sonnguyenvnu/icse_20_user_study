@Override public int updateByPk(String id,DepartmentEntity entity){
  publisher.publishEvent(new ClearPersonCacheEvent());
  return super.updateByPk(id,entity);
}
