public JPAQuery all(String entity){
  Query q=em().createQuery(createFindByQuery(entity,entity,null));
  return new JPAQuery(createFindByQuery(entity,entity,null),bindParameters(q));
}
