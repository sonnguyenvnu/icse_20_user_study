public static Builder getFieldMaskForProto(DataFetchingEnvironment environment,ImmutableMap<String,FragmentDefinition> fragmentsByName,Descriptor descriptor,String startAtFieldName){
  Builder maskFromSelectionBuilder=FieldMask.newBuilder();
  for (  Field field : environment.getFields()) {
    for (    Selection<?> selection1 : field.getSelectionSet().getSelections()) {
      if (selection1 instanceof Field) {
        Field field2=(Field)selection1;
        if (field2.getName().equals(startAtFieldName)) {
          for (          Selection<?> selection : field2.getSelectionSet().getSelections()) {
            maskFromSelectionBuilder.addAllPaths(getPathsForProto("",selection,descriptor,fragmentsByName));
          }
        }
      }
    }
  }
  return maskFromSelectionBuilder;
}
