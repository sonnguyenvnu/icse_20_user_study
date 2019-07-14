private static boolean providesFix(Tree tree,VisitorState state){
  return tree.accept(new TreeScanner<Boolean,Void>(){
    @Override public Boolean visitMethodInvocation(    MethodInvocationTree callTree,    Void unused){
      return super.visitMethodInvocation(callTree,unused) || DESCRIPTION_WITH_FIX.matches(callTree,state);
    }
    @Override public Boolean visitNewClass(    NewClassTree constructorTree,    Void unused){
      return super.visitNewClass(constructorTree,unused) || DESCRIPTION_CONSTRUCTOR.matches(constructorTree,state);
    }
    @Override public Boolean reduce(    Boolean m1,    Boolean m2){
      return firstNonNull(m1,false) || firstNonNull(m2,false);
    }
  }
,null);
}
