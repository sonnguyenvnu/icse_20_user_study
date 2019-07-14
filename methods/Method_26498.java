/** 
 * Don't flag methods that are empty or trivially delegate to a super-implementation. 
 */
private static boolean ignore(MethodTree method,VisitorState state){
  return firstNonNull(new TreeScanner<Boolean,Void>(){
    @Override public Boolean visitBlock(    BlockTree tree,    Void unused){
switch (tree.getStatements().size()) {
case 0:
        return true;
case 1:
      return scan(getOnlyElement(tree.getStatements()),null);
default :
    return false;
}
}
@Override public Boolean visitReturn(ReturnTree tree,Void unused){
return scan(tree.getExpression(),null);
}
@Override public Boolean visitExpressionStatement(ExpressionStatementTree tree,Void unused){
return scan(tree.getExpression(),null);
}
@Override public Boolean visitTypeCast(TypeCastTree tree,Void unused){
return scan(tree.getExpression(),null);
}
@Override public Boolean visitMethodInvocation(MethodInvocationTree node,Void aVoid){
ExpressionTree receiver=ASTHelpers.getReceiver(node);
return receiver instanceof IdentifierTree && ((IdentifierTree)receiver).getName().contentEquals("super") && overrides(ASTHelpers.getSymbol(method),ASTHelpers.getSymbol(node));
}
private boolean overrides(MethodSymbol sym,MethodSymbol other){
return !sym.isStatic() && !other.isStatic() && (((sym.flags() | other.flags()) & Flags.SYNTHETIC) == 0) && sym.name.contentEquals(other.name) && sym.overrides(other,sym.owner.enclClass(),state.getTypes(),false);
}
}
.scan(method.getBody(),null),false);
}
