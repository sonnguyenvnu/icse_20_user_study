private void checkComment(AbstractJavaAccessNode decl,Object data,MessageMaker maker){
  Comment comment=decl.comment();
  if (comment instanceof SingleLineComment || comment instanceof MultiLineComment) {
    addViolationWithMessage(data,decl,maker.make(),comment.getBeginLine(),comment.getEndLine());
  }
}
