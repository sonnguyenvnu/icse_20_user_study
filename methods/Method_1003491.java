@Override public Mono<Status> status(){
  return hostProvider.clusterInfo().map(it -> new ClientStatus(it.getNodes()));
}
