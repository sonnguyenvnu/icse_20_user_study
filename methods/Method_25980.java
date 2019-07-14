private Description maybeMatchReadByte(MethodTree readByteMethod,VisitorState state){
  if (readByteMethod.getBody() != null) {
    List<? extends StatementTree> statements=readByteMethod.getBody().getStatements();
    if (statements.size() == 1) {
      Tree tree=statements.get(0);
      if (tree.getKind() == Kind.RETURN && ASTHelpers.constValue(((ReturnTree)tree).getExpression()) != null) {
        return Description.NO_MATCH;
      }
    }
  }
  TreePath enclosingPath=ASTHelpers.findPathFromEnclosingNodeToTopLevel(state.getPath(),ClassTree.class);
  while (enclosingPath != null) {
    ClassTree klazz=(ClassTree)enclosingPath.getLeaf();
    if (JUnitMatchers.isTestCaseDescendant.matches(klazz,state) || hasAnnotation(JUnitMatchers.JUNIT4_RUN_WITH_ANNOTATION).matches(klazz,state)) {
      return Description.NO_MATCH;
    }
    enclosingPath=ASTHelpers.findPathFromEnclosingNodeToTopLevel(enclosingPath,ClassTree.class);
  }
  return describeMatch(readByteMethod);
}
