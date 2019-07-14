private void refactorInternalApplyMethods(MethodTree tree,SuggestedFix.Builder fixBuilder,Tree param,String mappedFunction){
  getMappingForApply(mappedFunction).ifPresent(apply -> {
    tree.accept(new TreeScanner<Void,Void>(){
      @Override public Void visitMethodInvocation(      MethodInvocationTree callTree,      Void unused){
        if (getSymbol(callTree).name.contentEquals("apply")) {
          Symbol receiverSym=getSymbol(getReceiver(callTree));
          if (receiverSym != null && receiverSym.equals(ASTHelpers.getSymbol(param))) {
            fixBuilder.replace(callTree.getMethodSelect(),receiverSym.name + "." + apply);
          }
        }
        return super.visitMethodInvocation(callTree,unused);
      }
    }
,null);
  }
);
}
