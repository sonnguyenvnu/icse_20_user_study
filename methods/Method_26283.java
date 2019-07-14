@Override protected void addFix(Description.Builder description,ExpressionTree tree,VisitorState state){
  TreePath parentPath=state.getPath().getParentPath();
  Tree parent=parentPath.getLeaf();
  SuggestedFix.Builder fix=SuggestedFix.builder();
  String streamType=SuggestedFixes.prettyType(state,fix,ASTHelpers.getReturnType(tree));
  if (parent instanceof MemberSelectTree) {
    StatementTree statement=state.findEnclosing(StatementTree.class);
    if (statement instanceof VariableTree) {
      VariableTree var=(VariableTree)statement;
      int pos=((JCTree)var).getStartPosition();
      int initPos=((JCTree)var.getInitializer()).getStartPosition();
      int eqPos=pos + state.getSourceForNode(var).substring(0,initPos - pos).lastIndexOf('=');
      fix.replace(eqPos,initPos,String.format(";\ntry (%s stream = %s) {\n%s =",streamType,state.getSourceForNode(tree),var.getName()));
    }
 else {
      fix.prefixWith(statement,String.format("try (%s stream = %s) {\n",streamType,state.getSourceForNode(tree)));
    }
    fix.replace(tree,"stream");
    fix.postfixWith(statement,"}");
    description.addFix(fix.build());
  }
 else   if (parent instanceof VariableTree) {
    Tree grandParent=parentPath.getParentPath().getLeaf();
    if (!(grandParent instanceof BlockTree)) {
      return;
    }
    List<? extends StatementTree> statements=((BlockTree)grandParent).getStatements();
    int idx=statements.indexOf(parent);
    int lastUse=idx;
    for (int i=idx + 1; i < statements.size(); i++) {
      boolean[] found={false};
      statements.get(i).accept(new TreeScanner<Void,Void>(){
        @Override public Void visitIdentifier(        IdentifierTree tree,        Void unused){
          if (Objects.equals(ASTHelpers.getSymbol(tree),ASTHelpers.getSymbol(parent))) {
            found[0]=true;
          }
          return null;
        }
      }
,null);
      if (found[0]) {
        lastUse=i;
      }
    }
    fix.prefixWith(parent,"try (");
    fix.replace(state.getEndPosition(((VariableTree)parent).getInitializer()),state.getEndPosition(parent),") {");
    fix.postfixWith(statements.get(lastUse),"}");
    description.addFix(fix.build());
  }
 else   if (parent instanceof EnhancedForLoopTree) {
    fix.prefixWith(parent,String.format("try (%s stream = %s) {\n",streamType,state.getSourceForNode(tree)));
    fix.replace(tree,"stream");
    fix.postfixWith(parent,"}");
    description.addFix(fix.build());
  }
 else   if (parent instanceof MethodInvocationTree) {
    Tree grandParent=parentPath.getParentPath().getLeaf();
    if (!(grandParent instanceof ExpressionStatementTree)) {
      return;
    }
    fix.prefixWith(parent,String.format("try (%s stream = %s) {\n",streamType,state.getSourceForNode(tree)));
    fix.replace(tree,"stream");
    fix.postfixWith(grandParent,"}");
    description.addFix(fix.build());
  }
}
