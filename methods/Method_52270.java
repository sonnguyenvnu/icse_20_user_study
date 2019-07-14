protected void assignCommentsToDeclarations(ASTCompilationUnit cUnit){
  SortedMap<Integer,Node> itemsByLineNumber=orderedCommentsAndDeclarations(cUnit);
  FormalComment lastComment=null;
  AbstractJavaAccessNode lastNode=null;
  for (  Entry<Integer,Node> entry : itemsByLineNumber.entrySet()) {
    Node value=entry.getValue();
    if (value instanceof AbstractJavaAccessNode) {
      AbstractJavaAccessNode node=(AbstractJavaAccessNode)value;
      if (lastComment != null && isCommentNotWithin(lastComment,lastNode,node) && isCommentBefore(lastComment,node)) {
        node.comment(lastComment);
        lastComment=null;
      }
      if (!(node instanceof AbstractJavaAccessTypeNode)) {
        lastNode=node;
      }
    }
 else     if (value instanceof FormalComment) {
      lastComment=(FormalComment)value;
    }
  }
}
