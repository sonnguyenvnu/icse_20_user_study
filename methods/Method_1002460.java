@Override public void callback(List<String> path,DataSchema schema){
  if (schema.getType() != DataSchema.Type.RECORD) {
    return;
  }
  if (schema.getProperties().get("avro") != null) {
    return;
  }
  RecordDataSchema recordSchema=(RecordDataSchema)schema;
  for (  RecordDataSchema.Field field : recordSchema.getFields()) {
    FieldOverride defaultValueOverride=_defaultValueOverrides.get(field);
    if (defaultValueOverride == null) {
      Object defaultData=field.getDefault();
      if (defaultData != null) {
        path.add(field.getName());
        _newDefaultSchema=null;
        Object newDefault=translateField(pathList(path),defaultData,field);
        _defaultValueOverrides.put(field,new FieldOverride(_newDefaultSchema,newDefault));
        path.remove(path.size() - 1);
      }
 else       if (field.getOptional()) {
        _defaultValueOverrides.put(field,FieldOverride.NULL_DEFAULT_VALUE);
      }
    }
  }
}
