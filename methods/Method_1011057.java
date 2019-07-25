public void execute(SNode node){
  SNodeOperations.replaceWithAnother(node,((SNode)replaceNode_QuickFix.this.getField("newNode")[0]));
}
