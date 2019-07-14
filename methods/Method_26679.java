@Override public Choice<Unifier> visitIdentifier(IdentifierTree ident,Unifier unifier){
  LocalVarBinding binding=unifier.getBinding(key());
  return Choice.condition(binding != null && Objects.equals(ASTHelpers.getSymbol(ident),binding.getSymbol()),unifier);
}
