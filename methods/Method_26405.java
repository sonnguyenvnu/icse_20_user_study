private static boolean matches(MethodInvocationTree tree){
  if (!tree.getArguments().isEmpty()) {
    return false;
  }
  MethodSymbol symbol=ASTHelpers.getSymbol(tree);
  if (symbol == null) {
    return false;
  }
switch (symbol.getSimpleName().toString()) {
case "now":
    return symbol.isStatic() && NOW_STATIC.contains(symbol.owner.getQualifiedName().toString());
case "dateNow":
  return !symbol.isStatic() && DATE_NOW_INSTANCE.contains(symbol.owner.getQualifiedName().toString());
case "systemDefaultZone":
return symbol.isStatic() && symbol.owner.getQualifiedName().contentEquals("java.time.Clock");
default :
return false;
}
}
