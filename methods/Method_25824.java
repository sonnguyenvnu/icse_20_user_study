private Description checkImmutable(VariableTree tree,VisitorState state,VarSymbol sym,String name){
  Type type=sym.type;
  if (type == null) {
    return Description.NO_MATCH;
  }
switch (name) {
case "serialVersionUID":
    return Description.NO_MATCH;
default :
  break;
}
if (Ascii.toUpperCase(name).equals(name)) {
return Description.NO_MATCH;
}
if (state.getTypes().unboxedTypeOrType(type).isPrimitive() || ASTHelpers.isSameType(type,state.getSymtab().stringType,state) || type.tsym.getKind() == ElementKind.ENUM) {
String constName=upperCaseReplace(name);
return buildDescription(tree).setMessage(String.format("%ss are immutable, field should be named '%s'",sym.type.tsym.getSimpleName(),constName)).addFix(SuggestedFixes.renameVariable(tree,constName,state)).build();
}
return Description.NO_MATCH;
}
