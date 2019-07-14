/** 
 * Check if the only methods invoked on the functional interface type are the descriptor method, e.g. don't rewrite uses of  {@link Predicate} in compilation units that call other methods like{#link Predicate#add}.
 */
boolean canFix(Tree type,Symbol sym,VisitorState state){
  Symbol descriptor=state.getTypes().findDescriptorSymbol(getType(type).asElement());
class Scanner extends TreePathScanner<Void,Void> {
    @Override public Void visitMethodInvocation(    MethodInvocationTree node,    Void unused){
      check(node);
      return super.visitMethodInvocation(node,null);
    }
    private void check(    MethodInvocationTree node){
      ExpressionTree lhs=node.getMethodSelect();
      if (!(lhs instanceof MemberSelectTree)) {
        return;
      }
      ExpressionTree receiver=((MemberSelectTree)lhs).getExpression();
      if (!Objects.equals(sym,getSymbol(receiver))) {
        return;
      }
      Symbol symbol=getSymbol(lhs);
      if (Objects.equals(descriptor,symbol)) {
        return;
      }
      fixable=false;
    }
  }
  Scanner scanner=new Scanner();
  scanner.scan(state.getPath().getCompilationUnit(),null);
  return scanner.fixable;
}
