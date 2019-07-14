@Override protected void assignCommentsToDeclarations(ASTCompilationUnit cUnit){
  SortedMap<Integer,Node> itemsByLineNumber=orderedComments(cUnit);
  Comment lastComment=null;
  AbstractJavaNode lastNode=null;
  for (  Entry<Integer,Node> entry : itemsByLineNumber.entrySet()) {
    Node value=entry.getValue();
    if (value instanceof AbstractJavaNode) {
      AbstractJavaNode node=(AbstractJavaNode)value;
      if (node instanceof ASTAnnotation) {
        continue;
      }
      if (lastComment != null && isCommentOneLineBefore(itemsByLineNumber,lastComment,lastNode,node)) {
        node.comment(lastComment);
        lastComment=null;
      }
      lastNode=node;
    }
 else     if (value instanceof Comment) {
      lastComment=(Comment)value;
    }
  }
}
