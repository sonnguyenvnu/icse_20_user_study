/** 
 * Finds all method invocations on the given symbol, and constructs a map of the called method's name, to the  {@link MethodInvocationTree} in which it was called.
 */
private ImmutableMultimap<String,MethodInvocationTree> methodCallsForSymbol(Symbol sym,ClassTree classTree){
  Builder<String,MethodInvocationTree> methodMap=ImmutableMultimap.builder();
  classTree.accept(new TreeScanner<Void,Void>(){
    @Override public Void visitMethodInvocation(    MethodInvocationTree callTree,    Void unused){
      if (sym.equals(getSymbol(getReceiver(callTree)))) {
        MethodSymbol methodSymbol=getSymbol(callTree);
        if (methodSymbol != null) {
          methodMap.put(methodSymbol.getSimpleName().toString(),callTree);
        }
      }
      return super.visitMethodInvocation(callTree,unused);
    }
  }
,null);
  return methodMap.build();
}
