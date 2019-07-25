private void compact(List<InstanceEvent> events){
  BinaryOperator<InstanceEvent> latestEvent=(e1,e2) -> e1.getVersion() > e2.getVersion() ? e1 : e2;
  Map<Class<?>,Optional<InstanceEvent>> latestPerType=events.stream().collect(groupingBy(InstanceEvent::getClass,reducing(latestEvent)));
  events.removeIf((e) -> !Objects.equals(e,latestPerType.get(e.getClass()).orElse(null)));
}
