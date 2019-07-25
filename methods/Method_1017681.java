public void visit(HeaderNode node){
  printBreakBeforeTag(node,"h" + node.getLevel());
}
