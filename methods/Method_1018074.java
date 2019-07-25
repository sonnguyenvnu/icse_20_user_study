/** 
 * Re-indexes the metastore to ensure it's in a consistent state.
 */
@PostConstruct public void initialize(){
  executor.execute(() -> metadataAccess.read(() -> datasourceProvider.getDatasources().stream().filter(DerivedDatasource.class::isInstance).map(DerivedDatasource.class::cast).filter(ds -> HIVE_DATASOURCE.equals(ds.getDatasourceType())).forEach(this::indexDerivedDatasource),MetadataAccess.SERVICE));
  search.commit(SearchIndex.DATASOURCES);
}
