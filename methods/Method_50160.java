public void readMembers(){
  for (  Method method : findMethods(moduleClass,Query.class)) {
    Query query=method.getAnnotationsByType(Query.class)[0];
    allQueriesInModule.add(methodToFieldDefinition(schemaDefinition,method,query.value(),query.fullName(),null));
  }
  for (  Method method : findMethods(moduleClass,Mutation.class)) {
    Mutation mutation=method.getAnnotationsByType(Mutation.class)[0];
    allMutationsInModule.add(methodToFieldDefinition(schemaDefinition,method,mutation.value(),mutation.fullName(),null));
  }
  final List<NodeDataFetcher> nodeDataFetchers=new ArrayList<>();
  final List<TypeModification> schemaModifications=new ArrayList<>();
  for (  Method method : findMethods(moduleClass,RelayNode.class)) {
    GraphQLFieldDefinition graphQLFieldDefinition=methodToFieldDefinition(schemaDefinition,method,"_NOT_USED_","_NOT_USED_",null);
    nodeDataFetchers.add(new NodeDataFetcher(graphQLFieldDefinition.getType().getName()){
      @Override public Object apply(      String s){
        try {
          return null;
        }
 catch (        Exception e) {
          throw new RuntimeException(e);
        }
      }
    }
);
  }
  try {
    for (    Method method : findMethods(moduleClass,SchemaModification.class)) {
      SchemaModification annotation=method.getAnnotationsByType(SchemaModification.class)[0];
      String name=annotation.addField();
      Class<?> typeClass=annotation.onType();
      Descriptor typeDescriptor=(Descriptor)typeClass.getMethod("getDescriptor").invoke(null);
      referencedDescriptors.add(typeDescriptor);
      schemaModifications.add(methodToTypeModification(schemaDefinition,method,name,typeDescriptor));
    }
  }
 catch (  IllegalAccessException|NoSuchMethodException|InvocationTargetException e) {
    throw new RuntimeException(e);
  }
  allMutationsInModule.addAll(extraMutations());
  try {
    for (    Field field : findQueryFields(moduleClass)) {
      field.setAccessible(true);
      allQueriesInModule.add((GraphQLFieldDefinition)field.get(schemaDefinition));
    }
    for (    Field field : findMutationFields(moduleClass)) {
      field.setAccessible(true);
      allMutationsInModule.add((GraphQLFieldDefinition)field.get(schemaDefinition));
    }
    for (    Field field : findTypeModificationFields(moduleClass)) {
      field.setAccessible(true);
      schemaBundleBuilder.modificationsBuilder().add((TypeModification)field.get(schemaDefinition));
    }
    for (    Field field : findExtraTypeFields(moduleClass)) {
      field.setAccessible(true);
      schemaBundleBuilder.fileDescriptorsBuilder().add((FileDescriptor)field.get(schemaDefinition));
    }
    schemaBundleBuilder.nodeDataFetchersBuilder().addAll(nodeDataFetchers);
    schemaBundleBuilder.modificationsBuilder().addAll(schemaModifications);
  }
 catch (  IllegalAccessException e) {
    throw new RuntimeException(e);
  }
}
