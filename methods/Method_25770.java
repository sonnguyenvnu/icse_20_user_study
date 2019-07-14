@Override public void visitIdent(JCTree.JCIdent tree){
  if (tree.sym.isStatic()) {
    return;
  }
switch (tree.sym.getKind()) {
case TYPE_PARAMETER:
case FIELD:
    if (!isOwnedBy(tree.sym,owner,state.getTypes())) {
      canPossiblyBeStatic=false;
    }
  break;
case METHOD:
if (!isOwnedBy(tree.sym,owner,state.getTypes())) {
  outerReferences.add((MethodSymbol)tree.sym);
}
break;
case CLASS:
Type enclosing=tree.type.getEnclosingType();
if (enclosing != null) {
enclosing.accept(new TypeVariableScanner(),null);
}
break;
default :
break;
}
}
