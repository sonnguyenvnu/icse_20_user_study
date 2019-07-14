@Nullable public static ValidationResult validate(ExpressionTree formatStringTree,List<? extends ExpressionTree> args,VisitorState state){
  if (MOCKITO_MATCHERS.matches(formatStringTree,state)) {
    return null;
  }
  Stream<String> formatStringValues=FormatStringValidation.constValues(formatStringTree);
  if (formatStringValues != null) {
    return FormatStringValidation.validate(null,ImmutableList.<ExpressionTree>builder().add(formatStringTree).addAll(args).build(),state);
  }
  Symbol formatStringSymbol=ASTHelpers.getSymbol(formatStringTree);
  if (!(formatStringSymbol instanceof VarSymbol)) {
    return ValidationResult.create(null,String.format("Format strings must be either literals or variables. Other expressions" + " are not valid.\n" + "Invalid format string: %s",formatStringTree));
  }
  if ((formatStringSymbol.flags() & (Flags.FINAL | Flags.EFFECTIVELY_FINAL)) == 0) {
    return ValidationResult.create(null,"All variables passed as @FormatString must be final or effectively final");
  }
  if (formatStringSymbol.getKind() == ElementKind.PARAMETER) {
    return validateFormatStringParameter(formatStringTree,formatStringSymbol,args,state);
  }
 else {
    return validateFormatStringVariable(formatStringTree,formatStringSymbol,args,state);
  }
}
