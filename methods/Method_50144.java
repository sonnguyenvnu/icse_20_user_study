private Object getValueForField(FieldDescriptor field,Object value,Message.Builder builder){
  if (field.getType() == FieldDescriptor.Type.MESSAGE) {
    Descriptor fieldTypeDescriptor=descriptorMapping.get(getReferenceName(field.getMessageType()));
    return createProtoBuf(fieldTypeDescriptor,builder.newBuilderForField(field),(Map<String,Object>)value);
  }
  if (field.getType() == FieldDescriptor.Type.ENUM) {
    EnumDescriptor enumDescriptor=enumMapping.get(ProtoToGql.getReferenceName(field.getEnumType()));
    return enumDescriptor.findValueByName(value.toString());
  }
  if (field.getType() == FieldDescriptor.Type.FLOAT) {
    if (value instanceof Double) {
      return ((Double)value).floatValue();
    }
  }
  return value;
}
