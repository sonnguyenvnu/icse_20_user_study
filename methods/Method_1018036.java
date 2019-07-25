@Override public ID resolve(Serializable id){
  if (id instanceof BaseDatasource.DatasourceId) {
    return (BaseDatasource.DatasourceId)id;
  }
 else {
    return new BaseDatasource.DatasourceId(id);
  }
}
