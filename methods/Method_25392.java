/** 
 * Matches an Annotation AST node if an argument to the annotation does not exist. 
 */
public static Matcher<AnnotationTree> doesNotHaveArgument(String argumentName){
  return new AnnotationDoesNotHaveArgument(argumentName);
}
