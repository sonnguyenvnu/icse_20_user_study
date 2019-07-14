private Description check(ExpressionTree tree,VisitorState state){
  if (state.findEnclosing(ImportTree.class) != null) {
    return Description.NO_MATCH;
  }
  Symbol sym=getSymbol(tree);
  if (sym == null) {
    return Description.NO_MATCH;
  }
  ClassSymbol receiver=getReceiver(tree,sym);
  if (receiver == null) {
    return Description.NO_MATCH;
  }
  Types types=state.getTypes();
  if (apiDiff.isClassUnsupported(Signatures.classDescriptor(receiver.type,types)) || classOrEnclosingClassIsForbiddenByAnnotation(receiver,state)) {
    return buildDescription(tree).setMessage(String.format("%s is not available",receiver)).build();
  }
  if (!(sym instanceof VarSymbol || sym instanceof MethodSymbol)) {
    return Description.NO_MATCH;
  }
  ClassMemberKey memberKey=ClassMemberKey.create(sym.getSimpleName().toString(),Signatures.descriptor(sym.type,types));
  ClassSymbol owner=sym.owner.enclClass();
  if (apiDiff.isMemberUnsupported(Signatures.classDescriptor(owner.type,types),memberKey) || hasAnnotationForbiddingUse(sym,state)) {
    return buildDescription(tree).setMessage(String.format("%s#%s is not available in %s",owner,sym,receiver)).build();
  }
  return Description.NO_MATCH;
}
