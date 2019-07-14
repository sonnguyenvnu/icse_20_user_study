@Override public Boolean visitIdentifier(IdentifierTree node,Unifier unifier){
  for (  LocalVarBinding localBinding : Iterables.filter(unifier.getBindings().values(),LocalVarBinding.class)) {
    if (localBinding.getSymbol().equals(ASTHelpers.getSymbol(node))) {
      return false;
    }
  }
  return true;
}
