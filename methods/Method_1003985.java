@Override public EndpointStatus join(InetSocketAddress endpoint,Map<String,InetSocketAddress> additionalEndpoints) throws JoinException, InterruptedException {
  LOG.log(Level.WARNING,"Joining a ServerSet without a shard ID is deprecated and will soon break.");
  return join(endpoint,additionalEndpoints,Optional.<Integer>absent());
}
