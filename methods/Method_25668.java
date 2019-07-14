/** 
 * Tokenizes as little of the  {@code tree} as possible to ensure we grab all the annotations. 
 */
private static List<ErrorProneToken> annotationTokens(Tree tree,VisitorState state,int annotationEnd){
  int endPos;
  if (tree instanceof JCMethodDecl) {
    JCMethodDecl methodTree=(JCMethodDecl)tree;
    endPos=methodTree.getBody() == null ? state.getEndPosition(methodTree) : methodTree.getBody().getStartPosition();
  }
 else   if (tree instanceof JCVariableDecl) {
    endPos=((JCVariableDecl)tree).getType().getStartPosition();
  }
 else   if (tree instanceof JCClassDecl) {
    JCClassDecl classTree=(JCClassDecl)tree;
    endPos=classTree.getMembers().isEmpty() ? state.getEndPosition(classTree) : classTree.getMembers().get(0).getStartPosition();
  }
 else {
    throw new AssertionError();
  }
  return state.getOffsetTokens(annotationEnd,endPos);
}
