public static Set<String> getPropertyKeys(JanusGraphVertex v){
  final Set<String> s=new HashSet<>();
  v.query().properties().forEach(p -> s.add(p.propertyKey().name()));
  return s;
}
