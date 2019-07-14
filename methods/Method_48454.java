public List<RegisteredAttributeClass<?>> convert(Configuration configuration){
  Set<String> attributeIds=configuration.getContainedNamespaces(GraphDatabaseConfiguration.CUSTOM_ATTRIBUTE_NS);
  List<RegisteredAttributeClass<?>> all=new ArrayList<>(attributeIds.size());
  for (  String attributeId : attributeIds) {
    final int position=getAttributePosition(attributeId);
    final Class<?> clazz=getAttributeClass(configuration,attributeId);
    final AttributeSerializer<?> serializer=getAttributeSerializer(configuration,attributeId);
    RegisteredAttributeClass reg=new RegisteredAttributeClass(position,clazz,serializer);
    if (all.contains(reg)) {
      throw new IllegalArgumentException("Duplicate attribute registration: " + reg);
    }
    all.add(reg);
  }
  return all;
}
