@Override public DataSetBuilder build(ID dataSourceId){
  return this.dsProvider.find(dataSourceId).map(Builder::new).orElseThrow(() -> new DataSourceNotFoundException(dataSourceId));
}
