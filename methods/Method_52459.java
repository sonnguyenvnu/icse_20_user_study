private void checkForNameChild(JavaNode node){
  if (node.getImage() != null) {
    add(new JavaNameOccurrence(node,node.getImage()));
  }
  if (node.jjtGetNumChildren() > 0 && node.jjtGetChild(0) instanceof ASTName) {
    ASTName grandchild=(ASTName)node.jjtGetChild(0);
    for (StringTokenizer st=new StringTokenizer(grandchild.getImage(),"."); st.hasMoreTokens(); ) {
      add(new JavaNameOccurrence(grandchild,st.nextToken()));
    }
  }
  if (node.jjtGetNumChildren() > 1 && node.jjtGetChild(1) instanceof ASTMethodReference) {
    ASTMethodReference methodRef=(ASTMethodReference)node.jjtGetChild(1);
    add(new JavaNameOccurrence(methodRef,methodRef.getImage()));
  }
  if (node instanceof ASTPrimarySuffix) {
    ASTPrimarySuffix suffix=(ASTPrimarySuffix)node;
    if (suffix.isArguments()) {
      JavaNameOccurrence occurrence=names.get(names.size() - 1);
      occurrence.setIsMethodOrConstructorInvocation();
      ASTArguments args=(ASTArguments)((ASTPrimarySuffix)node).jjtGetChild(0);
      occurrence.setArgumentCount(args.getArgumentCount());
    }
 else     if (suffix.jjtGetNumChildren() == 1 && suffix.jjtGetChild(0) instanceof ASTMemberSelector) {
      ASTMemberSelector member=(ASTMemberSelector)suffix.jjtGetChild(0);
      if (member.jjtGetNumChildren() == 1 && member.jjtGetChild(0) instanceof ASTMethodReference) {
        ASTMethodReference methodRef=(ASTMethodReference)member.jjtGetChild(0);
        add(new JavaNameOccurrence(methodRef,methodRef.getImage()));
      }
 else {
        add(new JavaNameOccurrence(member,member.getImage()));
      }
    }
  }
}
