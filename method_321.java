@Override public List<ArtifactMetadata> _XXXXX_(String key,String value,String repositoryId) throws MetadataRepositoryException {
  QueryResult<OrderedRows<String,String,String>> result=HFactory.createRangeSlicesQuery(keyspace,ss,ss,ss).setColumnFamily(cassandraArchivaManager.getProjectVersionMetadataFamilyName()).setColumnNames(PROJECT_ID.toString(),REPOSITORY_NAME.toString(),NAMESPACE_ID.toString(),PROJECT_VERSION.toString()).addEqualsExpression(key,value).execute();
  int count=result.get().getCount();
  if (count < 1) {
    return Collections.emptyList();
  }
  List<ArtifactMetadata> artifacts=new LinkedList<ArtifactMetadata>();
  for (  Row<String,String,String> row : result.get()) {
    try {
      artifacts.addAll(getArtifacts(getStringValue(row.getColumnSlice(),REPOSITORY_NAME),getStringValue(row.getColumnSlice(),NAMESPACE_ID),getStringValue(row.getColumnSlice(),PROJECT_ID),getStringValue(row.getColumnSlice(),PROJECT_VERSION)));
    }
 catch (    MetadataResolutionException e) {
      throw new IllegalStateException(e);
    }
  }
  return artifacts;
}