@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!BYTE_BUFFER_ARRAY_MATCHER.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  ExpressionTree receiver=tree;
  do {
    receiver=ASTHelpers.getReceiver(receiver);
    if (isValidInitializerOrNotAByteBuffer(receiver,state)) {
      return Description.NO_MATCH;
    }
  }
 while (receiver instanceof MethodInvocationTree);
  Symbol bufferSymbol=ASTHelpers.getSymbol(receiver);
  if (bufferSymbol == null) {
    return Description.NO_MATCH;
  }
  if (bufferSymbol.owner instanceof MethodSymbol) {
    MethodTree enclosingMethod=ASTHelpers.findMethod((MethodSymbol)bufferSymbol.owner,state);
    if (enclosingMethod == null || ValidByteBufferArrayScanner.scan(enclosingMethod,state,bufferSymbol)) {
      return Description.NO_MATCH;
    }
  }
  if (bufferSymbol.owner instanceof ClassSymbol) {
    ClassTree enclosingClass=ASTHelpers.findClass((ClassSymbol)bufferSymbol.owner,state);
    if (enclosingClass == null) {
      return Description.NO_MATCH;
    }
    Optional<? extends Tree> validMemberTree=enclosingClass.getMembers().stream().filter(memberTree -> ValidByteBufferArrayScanner.scan(memberTree,state,bufferSymbol)).findFirst();
    if (validMemberTree.isPresent()) {
      return Description.NO_MATCH;
    }
  }
  return describeMatch(tree);
}
