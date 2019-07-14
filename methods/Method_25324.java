/** 
 * Returns a fix that updates  {@code newValues} to the {@code parameterName} argument for {@code annotation}, regardless of whether there is already an argument. <p>N.B.:  {@code newValues} are source-code strings, not string literal values.
 */
public static Builder updateAnnotationArgumentValues(AnnotationTree annotation,String parameterName,Collection<String> newValues){
  if (annotation.getArguments().isEmpty()) {
    String parameterPrefix=parameterName.equals("value") ? "" : (parameterName + " = ");
    return SuggestedFix.builder().replace(annotation,annotation.toString().replaceFirst("\\(\\)","(" + parameterPrefix + newArgument(newValues) + ")"));
  }
  Optional<ExpressionTree> maybeExistingArgument=findArgument(annotation,parameterName);
  if (!maybeExistingArgument.isPresent()) {
    return SuggestedFix.builder().prefixWith(annotation.getArguments().get(0),parameterName + " = " + newArgument(newValues) + ", ");
  }
  ExpressionTree existingArgument=maybeExistingArgument.get();
  return SuggestedFix.builder().replace(existingArgument,newArgument(newValues));
}
