private static List<TypedAttributeCollector> load(){
  ServiceLoader<TypedAttributeCollector> loader=ServiceLoader.load(TypedAttributeCollector.class);
  List<TypedAttributeCollector> collectors=new ArrayList<>(10);
  for (  TypedAttributeCollector collector : loader) {
    collectors.add(collector);
  }
  return collectors;
}
