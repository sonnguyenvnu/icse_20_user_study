public static ASTNode getASTNodeAt(ASTNode root,int startJavaOffset,int stopJavaOffset){
  Messages.log("* getASTNodeAt");
  int length=stopJavaOffset - startJavaOffset;
  NodeFinder f=new NodeFinder(root,startJavaOffset,length);
  ASTNode node=f.getCoveredNode();
  if (node == null) {
    node=f.getCoveringNode();
  }
  if (node == null) {
    Messages.log("no node found");
  }
 else {
    Messages.log("found " + node.getClass().getSimpleName());
  }
  return node;
}
