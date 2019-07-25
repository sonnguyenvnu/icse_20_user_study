public static ProblemDescription describe(@Nullable SNodeReference node,String nodeRole){
  String msg;
  if (node == null) {
    msg=String.format("was %s: <unknown node reference>",nodeRole);
  }
 else {
    msg=String.format("was %s: %s",nodeRole,node.toString());
  }
  return new ProblemDescription(node,msg);
}
