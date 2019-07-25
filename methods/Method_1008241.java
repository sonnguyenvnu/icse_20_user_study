public String keyspace(){
  return getSettings().get(IndexMetaData.SETTING_KEYSPACE,ClusterService.indexToKsName(index.getName()));
}
