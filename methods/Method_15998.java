@Override @Transactional(readOnly=true) public E selectByPk(PK pk){
  if (StringUtils.isEmpty(pk)) {
    return null;
  }
  return createQuery().where(GenericEntity.id,pk).single();
}
