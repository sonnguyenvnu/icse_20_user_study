private void variableTypeFix(SuggestedFix.Builder fix,VisitorState state,Class<?> original,Class<?> replacement){
  Tree parent=state.getPath().getParentPath().getLeaf();
  Symbol sym;
switch (parent.getKind()) {
case VARIABLE:
    sym=ASTHelpers.getSymbol((VariableTree)parent);
  break;
case ASSIGNMENT:
sym=ASTHelpers.getSymbol(((AssignmentTree)parent).getVariable());
break;
default :
return;
}
if (!ASTHelpers.isSameType(sym.type,state.getTypeFromString(original.getCanonicalName()),state)) {
return;
}
state.getPath().getCompilationUnit().accept(new TreeScanner<Void,Void>(){
@Override public Void visitVariable(VariableTree node,Void aVoid){
if (sym.equals(ASTHelpers.getSymbol(node))) {
fix.replace(node.getType(),replacement.getSimpleName()).addImport(replacement.getCanonicalName());
}
return null;
}
}
,null);
}
