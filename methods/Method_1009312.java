@Nullable public Modular modular(){
  Modular modular=null;
  if (parent instanceof CallDefinitionClause) {
    CallDefinitionClause callDefinitionClause=(CallDefinitionClause)parent;
    modular=new org.elixir_lang.structure_view.element.modular.Quote(callDefinitionClause);
  }
 else   if (parent instanceof Modular) {
    modular=(Modular)parent;
  }
  return modular;
}
