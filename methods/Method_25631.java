private Description checkClosed(ExpressionTree tree,VisitorState state){
  MethodTree callerMethodTree=enclosingMethod(state);
  TreePath path=state.getPath();
  OUTER:   while (true) {
    TreePath prev=path;
    path=path.getParentPath();
switch (path.getLeaf().getKind()) {
case RETURN:
      if (callerMethodTree != null) {
        if (HAS_MUST_BE_CLOSED_ANNOTATION.matches(callerMethodTree,state)) {
          return NO_MATCH;
        }
        return describeMatch(tree,SuggestedFix.builder().prefixWith(callerMethodTree,"@MustBeClosed\n").addImport(MustBeClosed.class.getCanonicalName()).build());
      }
    break;
case CONDITIONAL_EXPRESSION:
  ConditionalExpressionTree conditionalExpressionTree=(ConditionalExpressionTree)path.getLeaf();
if (conditionalExpressionTree.getTrueExpression().equals(prev.getLeaf()) || conditionalExpressionTree.getFalseExpression().equals(prev.getLeaf())) {
  continue OUTER;
}
break;
case MEMBER_SELECT:
MemberSelectTree memberSelectTree=(MemberSelectTree)path.getLeaf();
if (memberSelectTree.getExpression().equals(prev.getLeaf())) {
Type type=getType(memberSelectTree);
Symbol sym=getSymbol(memberSelectTree);
Type streamType=state.getTypeFromString(Stream.class.getName());
if (isSubtype(sym.enclClass().asType(),streamType,state) && isSameType(type.getReturnType(),streamType,state)) {
path=path.getParentPath();
continue OUTER;
}
}
break;
case VARIABLE:
Symbol sym=getSymbol(path.getLeaf());
if (sym instanceof VarSymbol) {
VarSymbol var=(VarSymbol)sym;
if (var.getKind() == ElementKind.RESOURCE_VARIABLE || tryFinallyClose(var,path,state)) {
return NO_MATCH;
}
}
break;
default :
break;
}
Description.Builder description=buildDescription(tree);
addFix(description,tree,state);
return description.build();
}
}
