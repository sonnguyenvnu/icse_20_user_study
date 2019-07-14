static GraphQLArgument createArgument(Descriptor descriptor,String name){
  return GraphQLArgument.newArgument().name(name).type(getInputTypeReference(descriptor)).build();
}
