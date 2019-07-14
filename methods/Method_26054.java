private static Optional<String> getMappingForFunctionFromTree(Tree param){
  Optional<Type> type=ofNullable(ASTHelpers.getType(param));
  return (type == null) ? empty() : getMappingForFunction(type.get().toString());
}
