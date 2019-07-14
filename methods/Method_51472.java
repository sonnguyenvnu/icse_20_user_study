private static PropertyDescriptor<Boolean> booleanPropertyFor(String id,String label){
  PropertyDescriptor<Boolean> prop=PROPERTY_DESCRIPTORS_BY_ID.get(id);
  if (prop != null) {
    return prop;
  }
  prop=PropertyFactory.booleanProperty(id).defaultValue(true).desc("Include " + label + " column").build();
  PROPERTY_DESCRIPTORS_BY_ID.put(id,prop);
  return prop;
}
