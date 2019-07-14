@Override public Description matchMemberSelect(MemberSelectTree tree,VisitorState state){
  Symbol sym=getSymbol(tree);
  if (sym == null) {
    return NO_MATCH;
  }
switch (sym.getKind()) {
case FIELD:
    if (sym.getSimpleName().contentEquals("class") || sym.getSimpleName().contentEquals("super")) {
      return NO_MATCH;
    }
case METHOD:
  if (!sym.isStatic()) {
    return NO_MATCH;
  }
break;
default :
return NO_MATCH;
}
ClassSymbol owner=sym.owner.enclClass();
switch (tree.getExpression().getKind()) {
case MEMBER_SELECT:
case IDENTIFIER:
Symbol base=getSymbol(tree.getExpression());
if (base instanceof ClassSymbol && base.isSubClass(owner,state.getTypes())) {
return NO_MATCH;
}
break;
default :
}
SuggestedFix.Builder fix=SuggestedFix.builder();
String replacement;
boolean isMethod=sym instanceof MethodSymbol;
if (isMethod && Objects.equals(getSymbol(state.findEnclosing(ClassTree.class)),owner)) {
replacement=sym.getSimpleName().toString();
}
 else {
replacement=qualifyType(state,fix,sym);
}
fix.replace(tree,replacement);
if (tree.getExpression() instanceof MethodInvocationTree) {
StatementTree statement=state.findEnclosing(StatementTree.class);
if (statement != null) {
fix.prefixWith(statement,state.getSourceForNode(tree.getExpression()) + ";");
}
}
return buildDescription(tree).setMessage(String.format("Static %s %s should not be accessed from an object instance; instead use %s",isMethod ? "method" : "variable",sym.getSimpleName(),replacement)).addFix(fix.build()).build();
}
