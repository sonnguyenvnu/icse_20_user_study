@Override public Optional<DataSet> find(com.thinkbiganalytics.metadata.api.catalog.DataSet.ID id){
  return Optional.ofNullable(findById(id));
}
