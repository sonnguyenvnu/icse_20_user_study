private void findSafeVariablesInSignature(ASTMethod m){
  for (  ASTParameter p : m.findChildrenOfType(ASTParameter.class)) {
switch (p.getType().toLowerCase(Locale.ROOT)) {
case ID:
case INTEGER:
case BOOLEAN:
case DECIMAL:
case LONG:
case DOUBLE:
      safeVariables.add(Helper.getFQVariableName(p));
    break;
default :
  break;
}
}
}
