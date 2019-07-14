private static boolean isVariableClassCreator(VariableTree variableTree,VisitorState state,ClassType classType,Symbol parcelableCreatorSymbol){
  Tree typeTree=variableTree.getType();
  Type type=ASTHelpers.getType(typeTree);
  Types types=state.getTypes();
  Type superType=types.asSuper(type,parcelableCreatorSymbol);
  if (superType == null) {
    return false;
  }
  List<Type> typeArguments=superType.getTypeArguments();
  if (typeArguments.isEmpty()) {
    return true;
  }
  return ASTHelpers.isSubtype(classType,Iterables.getOnlyElement(typeArguments),state);
}
