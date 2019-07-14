@Override public PropertyDescriptor<E> build(Map<PropertyDescriptorField,String> fields){
  T builder=newBuilder(fields.get(PropertyDescriptorField.NAME));
  populate(builder,fields);
  builder.isDefinedInXML=true;
  return builder.build();
}
