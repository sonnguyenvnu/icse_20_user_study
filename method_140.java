@Override public Collection<ApplicationEntity> _XXXXX_(){
  List<ApplicationEntity> results=new ArrayList<>();
  try {
    results=queryService.query(selectSql,new RelationToApplicationEntity());
  }
 catch (  SQLException e) {
    LOGGER.error("Error to findAll ApplicationEntity: {}",e);
  }
  fillApplicationDesc(results);
  return results;
}