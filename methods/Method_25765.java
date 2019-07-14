@Override protected boolean matchArgument(ExpressionTree tree,VisitorState state){
  Type type=ASTHelpers.getType(tree);
  if (type == null) {
    return false;
  }
switch (state.getTypes().unboxedType(type).getTag()) {
case BYTE:
case CHAR:
case SHORT:
case INT:
case LONG:
case FLOAT:
case DOUBLE:
case BOOLEAN:
    break;
default :
  return false;
}
Symbol sym=ASTHelpers.getSymbol(tree);
if (sym instanceof Symbol.VarSymbol && isFinal(sym) && sym.isStatic()) {
return false;
}
return true;
}
