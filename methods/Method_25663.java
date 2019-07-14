private static Optional<? extends VariableTree> findParameterWithSymbol(List<? extends VariableTree> parameters,Symbol symbol){
  return parameters.stream().filter(parameter -> symbol.equals(ASTHelpers.getSymbol(parameter))).collect(toOptional());
}
