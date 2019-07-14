private static ValidationResult validateStringFromAssignment(Tree formatStringAssignment,ExpressionTree formatStringRhs,List<? extends ExpressionTree> args,VisitorState state){
  String value=ASTHelpers.constValue(formatStringRhs,String.class);
  if (value == null) {
    return ValidationResult.create(null,String.format("Local format string variables must only be assigned to compile time constant values." + " Invalid format string assignment: %s",formatStringAssignment));
  }
 else {
    return FormatStringValidation.validate(null,ImmutableList.<ExpressionTree>builder().add(formatStringRhs).addAll(args).build(),state);
  }
}
