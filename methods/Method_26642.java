@Nullable public Choice<Unifier> unify(@Nullable Symbol symbol,Unifier unifier){
  return symbol != null ? getName().unify(symbol.getQualifiedName(),unifier) : Choice.<Unifier>none();
}
