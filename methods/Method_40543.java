public Scope getTable(){
  if (table == null) {
    table=new Scope(null,Scope.ScopeType.SCOPE);
  }
  return table;
}
