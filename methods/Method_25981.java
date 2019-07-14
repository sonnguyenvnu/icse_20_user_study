private Description buildErrorMessage(MethodInvocationTree tree,String explanation){
  Description.Builder description=buildDescription(tree);
  String message=MESSAGE_BASE + explanation + ".";
  description.setMessage(message);
  return description.build();
}
