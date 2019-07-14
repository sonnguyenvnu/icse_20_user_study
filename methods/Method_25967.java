private Multimap<Type,VariableTree> partitionParametersByType(List<VariableTree> parameters,VisitorState state){
  Types types=state.getTypes();
  Multimap<Type,VariableTree> multimap=LinkedListMultimap.create();
  variables:   for (  VariableTree node : parameters) {
    Type type=types.unboxedTypeOrType(ASTHelpers.getType(node));
    for (    Type existingType : multimap.keySet()) {
      if (types.isSameType(existingType,type)) {
        multimap.put(existingType,node);
        continue variables;
      }
    }
    multimap.put(type,node);
  }
  return multimap;
}
