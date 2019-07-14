private static void computeFields(Class<?> clazz,Map<String,String> aliasMap,PropertyNamingStrategy propertyNamingStrategy,Map<String,FieldInfo> fieldInfoMap,Field[] fields){
  for (  Field field : fields) {
    if (Modifier.isStatic(field.getModifiers())) {
      continue;
    }
    JSONField fieldAnnotation=field.getAnnotation(JSONField.class);
    int ordinal=0, serialzeFeatures=0, parserFeatures=0;
    String propertyName=field.getName();
    String label=null;
    if (fieldAnnotation != null) {
      if (!fieldAnnotation.serialize()) {
        continue;
      }
      ordinal=fieldAnnotation.ordinal();
      serialzeFeatures=SerializerFeature.of(fieldAnnotation.serialzeFeatures());
      parserFeatures=Feature.of(fieldAnnotation.parseFeatures());
      if (fieldAnnotation.name().length() != 0) {
        propertyName=fieldAnnotation.name();
      }
      if (fieldAnnotation.label().length() != 0) {
        label=fieldAnnotation.label();
      }
    }
    if (aliasMap != null) {
      propertyName=aliasMap.get(propertyName);
      if (propertyName == null) {
        continue;
      }
    }
    if (propertyNamingStrategy != null) {
      propertyName=propertyNamingStrategy.translate(propertyName);
    }
    if (!fieldInfoMap.containsKey(propertyName)) {
      FieldInfo fieldInfo=new FieldInfo(propertyName,null,field,clazz,null,ordinal,serialzeFeatures,parserFeatures,null,fieldAnnotation,label);
      fieldInfoMap.put(propertyName,fieldInfo);
    }
  }
}
