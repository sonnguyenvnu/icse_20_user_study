private AttributeSerializer<?> getAttributeSerializer(Configuration configuration,String attributeId){
  Preconditions.checkArgument(configuration.has(GraphDatabaseConfiguration.CUSTOM_SERIALIZER_CLASS,attributeId));
  String serializerName=configuration.get(GraphDatabaseConfiguration.CUSTOM_SERIALIZER_CLASS,attributeId);
  try {
    Class<?> serializerClass=Class.forName(serializerName);
    return (AttributeSerializer)serializerClass.newInstance();
  }
 catch (  ClassNotFoundException e) {
    throw new IllegalArgumentException("Could not find serializer class " + serializerName);
  }
catch (  InstantiationException|IllegalAccessException e) {
    throw new IllegalArgumentException("Could not instantiate serializer class " + serializerName,e);
  }
}
