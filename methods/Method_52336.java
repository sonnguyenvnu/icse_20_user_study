/** 
 * Adds all methods called on this instance from within this Node.
 */
private static void addCalledMethodsOfNode(Node node,List<MethodInvocation> calledMethods,String className){
  List<ASTPrimaryExpression> expressions=new ArrayList<>();
  node.findDescendantsOfType(ASTPrimaryExpression.class,expressions,!(node instanceof AccessNode));
  addCalledMethodsOfNodeImpl(expressions,calledMethods,className);
}
