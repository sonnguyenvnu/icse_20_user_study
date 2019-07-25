@Provides @ConsumersScope List<ConsumerModule.Exposed> components(){
  final List<ConsumerModule.Exposed> consumers=new ArrayList<>();
  final AtomicInteger i=new AtomicInteger();
  for (  final ConsumerModule m : this.consumers) {
    final String id=m.id().orElseGet(() -> m.buildId(i.getAndIncrement()));
    final ConsumerModule.Depends depends=new Depends(reporter.newConsumer(id));
    consumers.add(m.module(primary,ingestion,depends,id));
  }
  return consumers;
}
