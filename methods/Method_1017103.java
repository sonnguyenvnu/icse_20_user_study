@Provides @ConsumersScope Set<Consumer> consumers(List<ConsumerModule.Exposed> components){
  final Set<Consumer> consumers=new HashSet<>();
  for (  final ConsumerModule.Exposed m : components) {
    consumers.add(m.consumer());
  }
  return consumers;
}
