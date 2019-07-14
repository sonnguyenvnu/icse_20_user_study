protected Mono<Application> toApplication(String name,Flux<Instance> instances){
  return instances.collectList().map(instanceList -> {
    Application group=new Application(name);
    group.setInstances(instanceList);
    group.setBuildVersion(getBuildVersion(instanceList));
    Tuple2<String,Instant> status=getStatus(instanceList);
    group.setStatus(status.getT1());
    group.setStatusTimestamp(status.getT2());
    return group;
  }
);
}
