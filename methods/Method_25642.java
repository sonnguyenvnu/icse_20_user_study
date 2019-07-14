protected final Fix getSuggestedFix(AnnotationTree annotationTree){
  List<String> values=new ArrayList<>();
  for (  ExpressionTree argumentTree : annotationTree.getArguments()) {
    AssignmentTree assignmentTree=(AssignmentTree)argumentTree;
    if (ASTHelpers.getSymbol(assignmentTree.getVariable()).getSimpleName().contentEquals("value")) {
      ExpressionTree expressionTree=assignmentTree.getExpression();
switch (expressionTree.getKind()) {
case STRING_LITERAL:
        values.add(((String)((JCTree.JCLiteral)expressionTree).value));
      break;
case NEW_ARRAY:
    NewArrayTree newArrayTree=(NewArrayTree)expressionTree;
  for (  ExpressionTree elementTree : newArrayTree.getInitializers()) {
    values.add((String)((JCTree.JCLiteral)elementTree).value);
  }
break;
default :
throw new AssertionError("Unknown kind: " + expressionTree.getKind());
}
processSuppressWarningsValues(values);
}
 else {
throw new AssertionError("SuppressWarnings has an element other than value=");
}
}
if (values.isEmpty()) {
return SuggestedFix.delete(annotationTree);
}
 else if (values.size() == 1) {
return SuggestedFix.replace(annotationTree,"@SuppressWarnings(\"" + values.get(0) + "\")");
}
 else {
return SuggestedFix.replace(annotationTree,"@SuppressWarnings({\"" + Joiner.on("\", \"").join(values) + "\"})");
}
}
