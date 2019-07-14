Session initializeSession(final String keyspaceName){
  final Session s=this.cluster.connect();
  if (this.cluster.getMetadata().getKeyspace(keyspaceName) != null) {
    return s;
  }
  final Configuration configuration=getStorageConfig();
  final Map<String,Object> replication=Match(configuration.get(REPLICATION_STRATEGY)).of(Case($("SimpleStrategy"),strategy -> HashMap.<String,Object>of("class",strategy,"replication_factor",configuration.get(REPLICATION_FACTOR))),Case($("NetworkTopologyStrategy"),strategy -> HashMap.<String,Object>of("class",strategy).merge(Array.of(configuration.get(REPLICATION_OPTIONS)).grouped(2).toMap(array -> Tuple.of(array.get(0),Integer.parseInt(array.get(1))))))).toJavaMap();
  s.execute(createKeyspace(keyspaceName).ifNotExists().with().replication(replication));
  return s;
}
