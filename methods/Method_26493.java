@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  MethodSymbol sym=ASTHelpers.getSymbol(tree);
  if (sym == null || !sym.isStatic() || !Flags.asFlagSet(sym.flags()).contains(Flag.NATIVE)) {
    return NO_MATCH;
  }
  MethodTree method=enclosingMethod(state);
  if (method == null) {
    return NO_MATCH;
  }
  MethodSymbol enclosing=ASTHelpers.getSymbol(method);
  if (enclosing == null || enclosing.isStatic() || enclosing.isConstructor()) {
    return NO_MATCH;
  }
  ImmutableList<Symbol> arguments=tree.getArguments().stream().map(ASTHelpers::getSymbol).filter(x -> x != null).collect(toImmutableList());
  if (arguments.stream().filter(x -> EnumSet.of(TypeKind.INT,TypeKind.LONG).contains(state.getTypes().unboxedTypeOrType(x.asType()).getKind())).noneMatch(arg -> arg.isMemberOf(enclosing.enclClass(),state.getTypes()))) {
    return NO_MATCH;
  }
  if (arguments.stream().anyMatch(arg -> arg.getSimpleName().contentEquals("this") && arg.isMemberOf(enclosing.enclClass(),state.getTypes()))) {
    return NO_MATCH;
  }
  Symbol finalizeSym=getFinalizer(state,enclosing.enclClass());
  if (finalizeSym == null || finalizeSym.equals(enclosing)) {
    return NO_MATCH;
  }
  if (finalizeSym.enclClass().equals(state.getSymtab().objectType.asElement())) {
    return NO_MATCH;
  }
  boolean[] sawFence={false};
  new TreeScanner<Void,Void>(){
    @Override public Void visitMethodInvocation(    MethodInvocationTree tree,    Void unused){
      if (FENCE_MATCHER.matches(tree,state)) {
        sawFence[0]=true;
      }
      return null;
    }
  }
.scan(state.getPath().getCompilationUnit(),null);
  if (sawFence[0]) {
    return NO_MATCH;
  }
  return describeMatch(tree);
}
