private EndpointStatus join(InetSocketAddress endpoint,Map<String,InetSocketAddress> auxEndpoints,Optional<Integer> shardId){
  LOG.warning("Attempt to join fixed server set ignored.");
  ServiceInstance joining=new ServiceInstance(ServerSets.toEndpoint(endpoint),Maps.transformValues(auxEndpoints,ServerSets.TO_ENDPOINT),Status.ALIVE);
  if (shardId.isPresent()) {
    joining.setShard(shardId.get());
  }
  if (!hosts.contains(joining)) {
    LOG.log(Level.SEVERE,"Joining instance " + joining + " does not match any member of the static set.");
  }
  return new EndpointStatus(){
    @Override public void leave() throws UpdateException {
      LOG.warning("Attempt to adjust state of fixed server set ignored.");
    }
    @Override public void update(    Status status) throws UpdateException {
      LOG.warning("Attempt to adjust state of fixed server set ignored.");
    }
  }
;
}
