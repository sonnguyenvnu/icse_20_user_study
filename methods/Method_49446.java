public ElasticSearchClient connect(Configuration config) throws IOException {
  log.debug("Configuring RestClient");
  final List<HttpHost> hosts=new ArrayList<>();
  final int defaultPort=config.has(INDEX_PORT) ? config.get(INDEX_PORT) : ElasticSearchIndex.HOST_PORT_DEFAULT;
  final String httpScheme=config.get(ElasticSearchIndex.SSL_ENABLED) ? "https" : "http";
  for (  String host : config.get(INDEX_HOSTS)) {
    String[] hostStringParts=host.split(":");
    String hostname=hostStringParts[0];
    int hostPort=defaultPort;
    if (hostStringParts.length == 2)     hostPort=Integer.parseInt(hostStringParts[1]);
    log.debug("Configured remote host: {} : {}",hostname,hostPort);
    hosts.add(new HttpHost(hostname,hostPort,httpScheme));
  }
  final RestClient rc=getRestClient(hosts.toArray(new HttpHost[hosts.size()]),config);
  final int scrollKeepAlive=config.get(ElasticSearchIndex.ES_SCROLL_KEEP_ALIVE);
  Preconditions.checkArgument(scrollKeepAlive >= 1,"Scroll keep-alive should be greater than or equal to 1");
  final RestElasticSearchClient client=getElasticSearchClient(rc,scrollKeepAlive);
  if (config.has(ElasticSearchIndex.BULK_REFRESH)) {
    client.setBulkRefresh(config.get(ElasticSearchIndex.BULK_REFRESH));
  }
  return client;
}
