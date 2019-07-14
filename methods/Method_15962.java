@Override default E selectByPk(PK id){
  return createRequest("/" + id).get().as(getEntityType());
}
