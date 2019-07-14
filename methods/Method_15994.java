@Override public E deleteByPk(PK pk){
  E old=selectByPk(pk);
  if (old == null) {
    return null;
  }
  if (old instanceof LogicalDeleteEntity) {
    LogicalDeleteEntity deleteEntity=(LogicalDeleteEntity)old;
    deleteEntity.setDeleted(true);
    deleteEntity.setDeleteTime(System.currentTimeMillis());
    createUpdate().set(deleteEntity::getDeleted).set(deleteEntity::getDeleteTime).where(GenericEntity.id,pk).exec();
  }
 else {
    if (!physicalDeleteByPk(pk)) {
      logger.warn("????????,??:{}",pk);
    }
  }
  return old;
}
