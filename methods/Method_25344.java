/** 
 * Gets the value for an argument, or null if the argument does not exist.
 * @param annotationTree the AST node for the annotation
 * @param name the name of the argument whose value to get
 * @return the value of the argument, or null if the argument does not exist
 */
@Nullable public static ExpressionTree getArgument(AnnotationTree annotationTree,String name){
  for (  ExpressionTree argumentTree : annotationTree.getArguments()) {
    if (argumentTree.getKind() != Tree.Kind.ASSIGNMENT) {
      continue;
    }
    AssignmentTree assignmentTree=(AssignmentTree)argumentTree;
    if (!assignmentTree.getVariable().toString().equals(name)) {
      continue;
    }
    ExpressionTree expressionTree=assignmentTree.getExpression();
    return expressionTree;
  }
  return null;
}
