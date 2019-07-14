public static Builder getFieldMaskForProto(DataFetchingEnvironment environment,ImmutableMap<String,FragmentDefinition> fragmentsByName,Descriptor descriptor){
  Builder maskFromSelectionBuilder=FieldMask.newBuilder();
  for (  Field field : environment.getFields()) {
    for (    Selection selection : field.getSelectionSet().getSelections()) {
      maskFromSelectionBuilder.addAllPaths(getPathsForProto("",selection,descriptor,fragmentsByName));
    }
  }
  return maskFromSelectionBuilder;
}
