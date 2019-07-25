public static ProblemDescription describe(SNode node,String nodeRole){
  SNodeReference nr;
  String msg;
  if (node == null) {
    nr=null;
    msg="null";
  }
 else {
    nr=node.getReference();
    msg=SNodeOperations.getDebugText(node);
  }
  return new ProblemDescription(nr,String.format("was %s: %s",nodeRole,msg));
}
