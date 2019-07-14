@Override protected void traverse(Tree tree,VisitorState state){
  ClassSymbol thisClass=ASTHelpers.getSymbol(state.findEnclosing(ClassTree.class));
  tree.accept(new TreeScanner<Void,Void>(){
    @Override public Void visitIdentifier(    IdentifierTree node,    Void unused){
      checkForThis(node,node.getName(),thisClass,state);
      return super.visitIdentifier(node,null);
    }
    @Override public Void visitMemberSelect(    MemberSelectTree node,    Void unused){
      checkForThis(node,node.getIdentifier(),thisClass,state);
      ExpressionTree left=node.getExpression();
      if ((left instanceof IdentifierTree && ((IdentifierTree)left).getName().contentEquals("this")) || (left instanceof MemberSelectTree && ((MemberSelectTree)left).getIdentifier().contentEquals("this"))) {
        return null;
      }
      return super.visitMemberSelect(node,unused);
    }
    @Override public Void visitAssignment(    AssignmentTree node,    Void unused){
      scan(node.getExpression(),null);
      return null;
    }
  }
,null);
}
