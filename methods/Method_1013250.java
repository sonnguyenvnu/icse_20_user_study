public void construct(boolean thm,LevelNode exp,ModuleNode oModNode,SymbolTable st,FormalParamNode[] parms){
  this.theorem=thm;
  this.body=exp;
  this.originallyDefinedInModule=oModNode;
  if (st != null) {
    st.addSymbol(name,this);
  }
  ;
  if (parms != null) {
    this.params=parms;
    this.arity=parms.length;
  }
  ;
}
