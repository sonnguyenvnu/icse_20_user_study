/** 
 * Helps  {@code validate()} validate a format string that is declared as a method parameter. 
 */
private static ValidationResult validateFormatStringParameter(ExpressionTree formatStringTree,Symbol formatStringSymbol,List<? extends ExpressionTree> args,VisitorState state){
  if (!isFormatStringParameter(formatStringSymbol,state)) {
    return ValidationResult.create(null,String.format("Format strings must be compile time constants or parameters annotated " + "@FormatString: %s",formatStringTree));
  }
  List<VarSymbol> ownerParams=((MethodSymbol)formatStringSymbol.owner).getParameters();
  int ownerFormatStringIndex=ownerParams.indexOf(formatStringSymbol);
  ImmutableList.Builder<Type> ownerFormatArgTypesBuilder=ImmutableList.builder();
  for (  VarSymbol paramSymbol : ownerParams.subList(ownerFormatStringIndex + 1,ownerParams.size())) {
    ownerFormatArgTypesBuilder.add(paramSymbol.type);
  }
  ImmutableList<Type> ownerFormatArgTypes=ownerFormatArgTypesBuilder.build();
  Types types=state.getTypes();
  ImmutableList.Builder<Type> calleeFormatArgTypesBuilder=ImmutableList.builder();
  for (  ExpressionTree formatArgExpression : args) {
    calleeFormatArgTypesBuilder.add(types.erasure(((JCExpression)formatArgExpression).type));
  }
  ImmutableList<Type> calleeFormatArgTypes=calleeFormatArgTypesBuilder.build();
  if (ownerFormatArgTypes.size() != calleeFormatArgTypes.size()) {
    return ValidationResult.create(null,String.format("The number of format arguments passed " + "with an @FormatString must match the number of format arguments in the " + "@FormatMethod header where the format string was declared.\n\t" + "Format args passed: %d\n\tFormat args expected: %d",calleeFormatArgTypes.size(),ownerFormatArgTypes.size()));
  }
 else {
    for (int i=0; i < calleeFormatArgTypes.size(); i++) {
      if (!ASTHelpers.isSameType(ownerFormatArgTypes.get(i),calleeFormatArgTypes.get(i),state)) {
        return ValidationResult.create(null,String.format("The format argument types passed " + "with an @FormatString must match the types of the format arguments in " + "the @FormatMethod header where the format string was declared.\n\t" + "Format arg types passed: %s\n\tFormat arg types expected: %s",calleeFormatArgTypes,ownerFormatArgTypes));
      }
    }
  }
  return null;
}
