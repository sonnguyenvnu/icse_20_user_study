@Override public Symbol.MethodSymbol resolveMethod(MethodInvocationTree node,GuardedByExpression base,javax.lang.model.element.Name identifier){
  Symbol baseSym=base.kind() == GuardedByExpression.Kind.THIS ? enclosingClass : base.type().asElement();
  return getMethod(baseSym,identifier.toString());
}
