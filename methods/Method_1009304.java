@NotNull @Override public ResolveResult[] resolve(@NotNull org.elixir_lang.reference.Atom atom,boolean incompleteCode){
  ElixirAtom element=atom.getElement();
  Resolvable resolvable=resolvable(element);
  return resolvable.resolve(element.getProject());
}
