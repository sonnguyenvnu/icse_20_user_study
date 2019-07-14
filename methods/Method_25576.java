/** 
 * Returns true iff the leaf node of the  {@code path} occurs in a JLS 8.3.1 static context. 
 */
private static boolean inStaticContext(TreePath path){
  Tree prev=path.getLeaf();
  path=path.getParentPath();
  ClassSymbol enclosingClass=ASTHelpers.getSymbol(ASTHelpers.findEnclosingNode(path,ClassTree.class));
  ClassSymbol directSuperClass=(ClassSymbol)enclosingClass.getSuperclass().tsym;
  for (  Tree tree : path) {
switch (tree.getKind()) {
case METHOD:
      return ASTHelpers.getSymbol(tree).isStatic();
case BLOCK:
    if (((BlockTree)tree).isStatic()) {
      return true;
    }
  break;
case VARIABLE:
VariableTree variableTree=(VariableTree)tree;
VarSymbol variableSym=ASTHelpers.getSymbol(variableTree);
if (variableSym.getKind() == ElementKind.FIELD) {
return Objects.equals(variableTree.getInitializer(),prev) && variableSym.isStatic();
}
break;
case METHOD_INVOCATION:
MethodSymbol methodSym=ASTHelpers.getSymbol((MethodInvocationTree)tree);
if (methodSym == null) {
return true;
}
if (methodSym.isConstructor() && (Objects.equals(methodSym.owner,enclosingClass) || Objects.equals(methodSym.owner,directSuperClass))) {
return true;
}
break;
default :
break;
}
prev=tree;
}
return false;
}
