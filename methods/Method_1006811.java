private void env(Criteria criteria){
  if (criteria instanceof Criteria.ResultMappedCriteria) {
    Criteria.ResultMappedCriteria resultMapped=(Criteria.ResultMappedCriteria)criteria;
    MapMapper mapMapper=resultMapped.getMapMapper();
    if (Objects.isNull(mapMapper)) {
      mapMapper=new MapMapper();
      resultMapped.setMapMapper(mapMapper);
    }
  }
}
