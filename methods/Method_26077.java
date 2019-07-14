private static boolean isExcluded(MethodTree tree,VisitorState state){
  MethodSymbol sym=ASTHelpers.getSymbol(tree);
  if (sym == null) {
    return true;
  }
  if (sym.isConstructor() || sym.getModifiers().contains(Modifier.NATIVE)) {
    return true;
  }
  if (!sym.isPrivate()) {
    return true;
  }
switch (sym.owner.enclClass().getNestingKind()) {
case TOP_LEVEL:
    break;
case MEMBER:
  if (sym.owner.enclClass().hasOuterInstance()) {
    return true;
  }
break;
case LOCAL:
case ANONYMOUS:
return true;
}
return SERIALIZATION_METHODS.matches(tree,state);
}
