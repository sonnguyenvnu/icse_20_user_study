private TypeModification methodToTypeModification(Object module,Method method,String name,Descriptor typeDescriptor){
  GraphQLFieldDefinition fieldDef=methodToFieldDefinition(module,method,name,name,typeDescriptor);
  return Type.find(typeDescriptor).addField(fieldDef);
}
