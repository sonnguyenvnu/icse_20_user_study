@Override public DepartmentEntity deleteByPk(String id){
  if (DefaultDSLQueryService.createQuery(positionDao).where(PositionEntity.departmentId,id).total() > 0) {
    throw new BusinessException("?????????,????!");
  }
  publisher.publishEvent(new ClearPersonCacheEvent());
  return super.deleteByPk(id);
}
