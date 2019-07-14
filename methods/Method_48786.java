private Map<String,Object> deserializeVertexProperties(Map<Object,Object> map){
  HashMap<String,Object> deserializedProperties=new HashMap<>();
  map.forEach((key,value) -> {
    if (value instanceof List) {
      if (((List)value).size() > 1) {
        log.warn("Your configuration management graph is an a bad state. Please " + "ensure each vertex property is not supplied a Collection as a value. The behavior " + "of the class' APIs are henceforth unpredictable until this is fixed.");
      }
      deserializedProperties.put((String)key,((List)value).get(0));
    }
  }
);
  return deserializedProperties;
}
