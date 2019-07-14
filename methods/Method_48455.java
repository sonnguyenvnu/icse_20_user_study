private Class<?> getAttributeClass(Configuration configuration,String attributeId){
  String classname=configuration.get(GraphDatabaseConfiguration.CUSTOM_ATTRIBUTE_CLASS,attributeId);
  try {
    return Class.forName(classname);
  }
 catch (  ClassNotFoundException e) {
    throw new IllegalArgumentException("Could not find attribute class " + classname,e);
  }
}
