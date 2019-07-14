/** 
 * Returns a fix that appends  {@code newValues} to the {@code parameterName} argument for {@code annotation}, regardless of whether there is already an argument. <p>N.B.:  {@code newValues} are source-code strings, not string literal values.
 */
public static Builder addValuesToAnnotationArgument(AnnotationTree annotation,String parameterName,Collection<String> newValues,VisitorState state){
  if (annotation.getArguments().isEmpty()) {
    String parameterPrefix=parameterName.equals("value") ? "" : (parameterName + " = ");
    return SuggestedFix.builder().replace(annotation,annotation.toString().replaceFirst("\\(\\)","(" + parameterPrefix + newArgument(newValues) + ")"));
  }
  Optional<ExpressionTree> maybeExistingArgument=findArgument(annotation,parameterName);
  if (!maybeExistingArgument.isPresent()) {
    return SuggestedFix.builder().prefixWith(annotation.getArguments().get(0),parameterName + " = " + newArgument(newValues) + ", ");
  }
  ExpressionTree existingArgument=maybeExistingArgument.get();
  if (!existingArgument.getKind().equals(NEW_ARRAY)) {
    return SuggestedFix.builder().replace(existingArgument,newArgument(state.getSourceForNode(existingArgument),newValues));
  }
  NewArrayTree newArray=(NewArrayTree)existingArgument;
  if (newArray.getInitializers().isEmpty()) {
    return SuggestedFix.builder().replace(newArray,newArgument(newValues));
  }
 else {
    return SuggestedFix.builder().postfixWith(getLast(newArray.getInitializers()),", " + Joiner.on(", ").join(newValues));
  }
}
