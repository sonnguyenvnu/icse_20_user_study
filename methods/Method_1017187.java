public Module module(){
  final SimpleModule m=new SimpleModule("aggregationRegistry");
  for (  final Map.Entry<Class<? extends AggregationInstance>,String> e : instanceMap.entrySet()) {
    m.registerSubtypes(new NamedType(e.getKey(),e.getValue()));
  }
  for (  final Map.Entry<Class<? extends Aggregation>,String> e : definitionMap.entrySet()) {
    m.registerSubtypes(new NamedType(e.getKey(),e.getValue()));
  }
  return m;
}
