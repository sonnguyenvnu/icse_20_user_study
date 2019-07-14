private ImmutableMultimap<String,MethodInvocationTree> methodCallsForSymbol(Symbol sym,ClassTree classTree){
  ImmutableMultimap.Builder<String,MethodInvocationTree> methodMap=ImmutableMultimap.builder();
  classTree.accept(new TreeScanner<Void,Void>(){
    @Override public Void visitMethodInvocation(    MethodInvocationTree callTree,    Void unused){
      final MethodSymbol methodSymbol=getSymbol(callTree);
      if (methodSymbol != null && sym.equals(methodSymbol)) {
        methodMap.put(methodSymbol.toString(),callTree);
      }
      return super.visitMethodInvocation(callTree,unused);
    }
  }
,null);
  return methodMap.build();
}
