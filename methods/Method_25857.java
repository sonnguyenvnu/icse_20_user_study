private Description checkTree(Tree tree,MethodSymbol sym,VisitorState state){
  if (!hasAnnotation(sym,DO_NOT_CALL,state)) {
    return NO_MATCH;
  }
  String doNotCall=getDoNotCallValue(sym);
  StringBuilder message=new StringBuilder("This method should not be called");
  if (doNotCall.isEmpty()) {
    message.append(", see its documentation for details.");
  }
 else {
    message.append(": ").append(doNotCall);
  }
  return buildDescription(tree).setMessage(message.toString()).build();
}
