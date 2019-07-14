@Override public JSR303AnnotationInfo parse(Map<String,Object> configMap){
  JSR303AnnotationInfo info=new JSR303AnnotationInfo();
  info.setAnnotation(getAnnotationType());
  Map<String,Object> properties=new HashMap<>();
  propertyMappings.forEach(mapping -> {
    Object value=mapping.getConverter().apply(configMap.get(mapping.getName()));
    if (!StringUtils.isEmpty(value)) {
      properties.put(mapping.getName(),value);
    }
  }
);
  List<Object> groups=null;
  Object groupObject=new JSONObject(configMap).get("groups");
  if (groupObject instanceof JSONArray) {
    groups=((JSONArray)groupObject);
  }
  if (groupObject instanceof String) {
    groups=Arrays.asList(((String)groupObject).split("[,]"));
  }
  if (!CollectionUtils.isEmpty(groups)) {
    properties.put("groups",groups.stream().map(obj -> {
      if ("create".equals(obj)) {
        return CreateGroup.class;
      }
 else       if ("update".equals(obj)) {
        return UpdateGroup.class;
      }
 else {
        try {
          return Class.forName(String.valueOf(obj));
        }
 catch (        ClassNotFoundException e) {
          return CreateGroup.class;
        }
      }
    }
).toArray());
  }
  info.setProperties(properties);
  return info;
}
