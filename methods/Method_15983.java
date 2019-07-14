@Override public E deleteByPk(PK id){
  E old=selectByPk(id);
  assertNotNull(old);
  if (StringUtils.isEmpty(old.getPath())) {
    getDao().deleteByPk(id);
  }
 else {
    DefaultDSLDeleteService.createDelete(getDao()).where().like$(TreeSupportEntity.path,old.getPath()).exec();
  }
  return old;
}
