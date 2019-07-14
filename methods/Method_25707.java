static InvocationInfo createFromNewClass(NewClassTree tree,MethodSymbol symbol,VisitorState state){
  return new AutoValue_InvocationInfo(tree,symbol,ImmutableList.copyOf(tree.getArguments()),getFormalParametersWithoutVarArgs(symbol),state);
}
