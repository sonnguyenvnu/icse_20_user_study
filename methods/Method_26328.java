@Override public Symbol resolveSelect(GuardedByExpression base,MemberSelectTree node){
  Symbol baseSym=base.kind() == GuardedByExpression.Kind.THIS ? enclosingClass : base.type().asElement();
  return getField(baseSym,node.getIdentifier().toString());
}
