@Override public Choice<Unifier> visitVariable(final VariableTree decl,Unifier unifier){
  return Choice.condition(unifier.getBinding(key()) == null,unifier).thenChoose(unifications(getType(),decl.getType())).thenChoose(unifications(getInitializer(),decl.getInitializer())).transform(new Function<Unifier,Unifier>(){
    @Override public Unifier apply(    Unifier unifier){
      unifier.putBinding(key(),LocalVarBinding.create(ASTHelpers.getSymbol(decl),decl.getModifiers()));
      return unifier;
    }
  }
);
}
