protected void checkCommentsBetweenDeclarations(ASTCompilationUnit cUnit,Object data){
  SortedMap<Integer,Node> itemsByLineNumber=orderedCommentsAndDeclarations(cUnit);
  Comment lastComment=null;
  boolean suppressWarning=false;
  CommentPatternEnum commentPattern=CommentPatternEnum.NONE;
  for (  Entry<Integer,Node> entry : itemsByLineNumber.entrySet()) {
    Node value=entry.getValue();
    if (value instanceof JavaNode) {
      JavaNode node=(JavaNode)value;
      if (lastComment != null && isCommentBefore(lastComment,node)) {
        if (!CommentPatternEnum.NONE.equals(commentPattern)) {
          boolean statementOutsideMethod=CommentPatternEnum.STATEMENT.equals(commentPattern) && !(node instanceof ASTBlockStatement);
          if (!statementOutsideMethod) {
            addViolationWithMessage(data,node,getMessage(),lastComment.getBeginLine(),lastComment.getEndLine());
          }
        }
        lastComment=null;
      }
      suppressWarning=false;
      commentPattern=CommentPatternEnum.NONE;
    }
 else     if (value instanceof Comment) {
      lastComment=(Comment)value;
      String content=lastComment.getImage();
      if (!suppressWarning) {
        suppressWarning=SUPPRESS_PATTERN.matcher(content).matches();
      }
      if (!suppressWarning && CommentPatternEnum.NONE.equals(commentPattern)) {
        commentPattern=this.scanCommentedCode(content);
      }
    }
  }
}
