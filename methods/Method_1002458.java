@Override public void callback(List<String> path,DataSchema schema){
  if (schema.getType() != DataSchema.Type.RECORD) {
    return;
  }
  RecordDataSchema recordSchema=(RecordDataSchema)schema;
  for (  RecordDataSchema.Field field : recordSchema.getFields()) {
    DataSchema fieldSchema=field.getType();
    boolean isUnion=fieldSchema.getDereferencedType() == DataSchema.Type.UNION;
    field.setOptional(false);
    if (isUnion) {
      UnionDataSchema unionSchema=(UnionDataSchema)fieldSchema;
      if (unionSchema.contains(NULL_DATA_SCHEMA.getUnionMemberKey())) {
        List<UnionDataSchema.Member> nonNullMembers=unionSchema.getMembers().stream().filter(member -> member.getType().getType() != NULL_DATA_SCHEMA.getType()).collect(Collectors.toCollection(ArrayList::new));
        if (nonNullMembers.size() == 1) {
          field.setType(nonNullMembers.get(0).getType());
        }
 else {
          StringBuilder errorMessages=null;
          unionSchema.setMembers(nonNullMembers,errorMessages);
        }
        field.setOptional(true);
      }
    }
  }
}
