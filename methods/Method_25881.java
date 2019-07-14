@Override public Description matchTypeCast(TypeCastTree tree,VisitorState state){
  Tree parent=state.getPath().getParentPath().getLeaf();
  if (!(parent instanceof BinaryTree)) {
    return NO_MATCH;
  }
  BinaryTree binop=(BinaryTree)parent;
  if (!binop.getLeftOperand().equals(tree)) {
    return NO_MATCH;
  }
  if (binop.getKind() != Kind.MULTIPLY) {
    return NO_MATCH;
  }
  Type castType=ASTHelpers.getType(tree.getType());
  Type operandType=ASTHelpers.getType(tree.getExpression());
  if (castType == null || operandType == null) {
    return NO_MATCH;
  }
  Symtab symtab=state.getSymtab();
  if (isSameType(ASTHelpers.getType(parent),symtab.stringType,state)) {
    return NO_MATCH;
  }
switch (castType.getKind()) {
case LONG:
case INT:
case SHORT:
case CHAR:
case BYTE:
    break;
default :
  return NO_MATCH;
}
switch (operandType.getKind()) {
case FLOAT:
case DOUBLE:
break;
default :
return NO_MATCH;
}
if (BLACKLIST.matches(tree.getExpression(),state)) {
return NO_MATCH;
}
if (POW.matches(tree.getExpression(),state)) {
MethodInvocationTree pow=(MethodInvocationTree)tree.getExpression();
if (pow.getArguments().stream().map(ASTHelpers::getType).filter(x -> x != null).map(state.getTypes()::unboxedTypeOrType).map(Type::getKind).allMatch(INTEGRAL::contains)) {
return NO_MATCH;
}
}
Tree enclosing=binop;
TreePath path=state.getPath().getParentPath().getParentPath();
while (path != null) {
if (!(path.getLeaf() instanceof BinaryTree)) {
break;
}
BinaryTree enclosingBinop=(BinaryTree)path.getLeaf();
if (!enclosingBinop.getLeftOperand().equals(enclosing)) {
break;
}
enclosing=enclosingBinop;
path=path.getParentPath();
}
return buildDescription(tree).addFix(SuggestedFix.builder().prefixWith(tree.getExpression(),"(").postfixWith(enclosing,")").build()).addFix(SuggestedFix.builder().prefixWith(tree,"(").postfixWith(tree,")").build()).build();
}
