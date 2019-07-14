public SchemaBundle createBundle(){
  Namespace namespaceAnnotation=findClassAnnotation(moduleClass,Namespace.class);
  if (namespaceAnnotation == null) {
    schemaBundleBuilder.mutationFieldsBuilder().addAll(allMutationsInModule);
    schemaBundleBuilder.queryFieldsBuilder().addAll(allQueriesInModule);
  }
 else {
    String namespace=namespaceAnnotation.value();
    if (!allQueriesInModule.isEmpty()) {
      schemaBundleBuilder.queryFieldsBuilder().add(GraphQLFieldDefinition.newFieldDefinition().staticValue("").name(namespace).description(namespace).type(GraphQLObjectType.newObject().name("_QUERY_FIELD_GROUP_" + namespace).fields(allQueriesInModule).build()).build());
    }
    if (!allMutationsInModule.isEmpty()) {
      schemaBundleBuilder.mutationFieldsBuilder().add(GraphQLFieldDefinition.newFieldDefinition().staticValue("").name(namespace).description(namespace).type(GraphQLObjectType.newObject().name("_MUTATION_FIELD_GROUP_" + namespace).fields(allMutationsInModule).build()).build());
    }
  }
  referencedDescriptors.build().forEach(descriptor -> schemaBundleBuilder.fileDescriptorsBuilder().add(descriptor.getFile()));
  return schemaBundleBuilder.build();
}
