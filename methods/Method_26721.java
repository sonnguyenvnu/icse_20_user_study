public static ImmutableMap<String,VarSymbol> freeExpressionVariables(MethodTree templateMethodDecl){
  ImmutableMap.Builder<String,VarSymbol> builder=ImmutableMap.builder();
  for (  VariableTree param : templateMethodDecl.getParameters()) {
    builder.put(param.getName().toString(),ASTHelpers.getSymbol(param));
  }
  return builder.build();
}
