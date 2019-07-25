@Override public void callback(List<String> path,DataSchema schema){
  if (schema.getType() != DataSchema.Type.RECORD) {
    return;
  }
  RecordDataSchema recordSchema=(RecordDataSchema)schema;
  for (  RecordDataSchema.Field field : recordSchema.getFields()) {
    Object defaultData=field.getDefault();
    if (defaultData != null) {
      path.add(DataSchemaConstants.DEFAULT_KEY);
      Object newDefault=translateField(pathList(path),defaultData,field);
      path.remove(path.size() - 1);
      field.setDefault(newDefault);
    }
  }
}
