private static void build(ExtensibleTypeDescriptor typeDescr,ExtensibleTypeBuilder bldr){
  bldr.description(typeDescr.getDescription());
  for (  FieldDescriptor field : typeDescr.getFields()) {
    bldr.field(field.getName()).displayName(field.getDisplayName()).description(field.getDescription()).type(com.thinkbiganalytics.metadata.api.extension.FieldDescriptor.Type.valueOf(field.getType().name())).collection(field.isCollection()).required(field.isRequired()).add();
  }
}
