@Override public boolean matches(AnnotationTree annotationTree,VisitorState state){
  ExpressionTree expressionTree=AnnotationMatcherUtils.getArgument(annotationTree,element);
  if (expressionTree == null) {
    return false;
  }
  expressionTree=ASTHelpers.stripParentheses(expressionTree);
  if (expressionTree instanceof NewArrayTree) {
    NewArrayTree arrayTree=(NewArrayTree)expressionTree;
    for (    ExpressionTree elementTree : arrayTree.getInitializers()) {
      if (valueMatcher.matches(elementTree,state)) {
        return true;
      }
    }
    return false;
  }
  return valueMatcher.matches(expressionTree,state);
}
